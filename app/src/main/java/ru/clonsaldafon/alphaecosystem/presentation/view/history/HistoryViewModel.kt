package ru.clonsaldafon.alphaecosystem.presentation.view.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.alphaecosystem.domain.LoadHistoryUseCase
import ru.clonsaldafon.alphaecosystem.presentation.UiState
import ru.clonsaldafon.alphaecosystem.presentation.model.Card
import ru.clonsaldafon.alphaecosystem.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val useCase: LoadHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState>
        get() = _uiState

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnHistoryLoaded -> updateCards(event.cards)
        }
    }

    fun loadHistory(): Boolean {
        try {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }

            viewModelScope.launch {
                when (val response = useCase().toUiState()) {
                    is UiState.Success -> {
                        val cards = mutableListOf<Card>()
                        response.value?.forEach {
                            cards.add(
                                Card(
                                    number = it.number,
                                    country = it.country,
                                    coordinates = it.coordinates,
                                    type = it.type,
                                    phone = it.phone,
                                    website = it.website,
                                    city = it.city
                                )
                            )
                        }

                        _uiState.update {
                            it.copy(
                                cards = cards
                            )
                        }
                    }
                    is UiState.Failure -> {
                        _uiState.update {
                            it.copy(
                                error = "An error occurred while trying to download " +
                                        "the query history"
                            )
                        }
                    }
                    is UiState.Loading -> {}
                }
            }
        } catch (e: Exception) {
            Log.e("error", e.message!!)
        } finally {
            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }

        return !_uiState.value.cards.isNullOrEmpty()
    }

    private fun updateCards(value: List<Card>?) {
        _uiState.update {
            it.copy(
                cards = value,
                isLoading = false
            )
        }
    }

}