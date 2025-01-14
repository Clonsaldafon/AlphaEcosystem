package ru.clonsaldafon.alphaecosystem.presentation.view.history

import ru.clonsaldafon.alphaecosystem.presentation.model.Card

data class HistoryUiState(
    val cards: List<Card>? = null,
    val isLoading: Boolean = false
) {

    fun reset() = copy(
        cards = null,
        isLoading = false
    )

}
