package ru.clonsaldafon.alphaecosystem.presentation.view.history

import ru.clonsaldafon.alphaecosystem.presentation.model.Card

sealed class HistoryEvent {
    data class OnHistoryLoaded(val cards: List<Card>?) : HistoryEvent()
}