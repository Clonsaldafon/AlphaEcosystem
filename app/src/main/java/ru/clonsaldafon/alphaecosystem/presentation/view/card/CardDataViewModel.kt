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
                                    country = response.value?.country?.name,
                                    coordinates = "${response.value?.country?.latitude} - " +
                                            "${response.value?.country?.longitude}",
                                    type = response.value?.type,
                                    phone = response.value?.bank?.phone,
                                    website = response.value?.bank?.url,
                                    city = response.value?.bank?.city
                                )
                            )
                        }
                    }
                    is UiState.Failure -> {}
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