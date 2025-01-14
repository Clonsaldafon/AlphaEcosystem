package ru.clonsaldafon.alphaecosystem.presentation.view.card

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.alphaecosystem.domain.LoadCardDataUseCase
import ru.clonsaldafon.alphaecosystem.presentation.UiState
import ru.clonsaldafon.alphaecosystem.presentation.model.Card
import ru.clonsaldafon.alphaecosystem.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class CardDataViewModel @Inject constructor(
    private val useCase: LoadCardDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardDataUiState())
    val uiState: StateFlow<CardDataUiState>
        get() = _uiState

    fun onEvent(event: CardDataEvent) {
        when (event) {
            is CardDataEvent.OnNumberChanged -> updateNumber(event.value)
            is CardDataEvent.OnSubmit -> loadData(event.onComplete)
        }
    }

    private fun updateNumber(value: String) {
        _uiState.update {
            it.copy(
                number = value
            )
        }
    }

    private fun loadData(
        onComplete: (card: Card?) -> Unit
    ) {
        try {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }

            viewModelScope.launch {
                val response = useCase(
                    number = _uiState.value.number.toInt()
                ).toUiState()

                when (response) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                card = Card(
                                    number = _uiState.value.number.toInt(),
                                    scheme = response.value?.scheme,
                                    type = response.value?.type,
                                    bankName = response.value?.bank?.name,
                                    city = response.value?.bank?.city,
                                    website = response.value?.bank?.url,
                                    phone = response.value?.bank?.phone,
                                    brand = response.value?.brand,
                                    country = "${response.value?.country?.emoji} " +
                                            "${response.value?.country?.name}",
                                    latitude = response.value?.country?.latitude,
                                    longitude = response.value?.country?.longitude
                                )
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            404 -> {
                                _uiState.update {
                                    it.copy(
                                        error = "The card was not found"
                                    )
                                }
                            }
                            429 -> {
                                _uiState.update {
                                    it.copy(
                                        error = "You can send no more than 5 requests per hour"
                                    )
                                }
                            }
                        }
                    }
                    is UiState.Loading -> {}
                }
            }
        } catch (e: Exception) {
            Log.e("error", e.message!!)
        } finally {
            onComplete(_uiState.value.card)

            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

}