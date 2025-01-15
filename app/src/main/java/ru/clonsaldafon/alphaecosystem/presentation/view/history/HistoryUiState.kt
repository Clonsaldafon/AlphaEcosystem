package ru.clonsaldafon.alphaecosystem.presentation.view.history

import ru.clonsaldafon.alphaecosystem.presentation.model.Card

data class HistoryUiState(
    val cards: List<Card>? = null,
    val error: String = "",
    val isLoading: Boolean = false
) {

    fun reset() = copy(
        cards = null,
        error = "",
        isLoading = false
    )

}
