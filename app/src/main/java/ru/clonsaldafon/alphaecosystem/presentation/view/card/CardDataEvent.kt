package ru.clonsaldafon.alphaecosystem.presentation.view.card

import ru.clonsaldafon.alphaecosystem.presentation.model.Card

sealed class CardDataEvent {
    data class OnNumberChanged(val value: String) : CardDataEvent()
    data class OnSubmit(val onComplete: (card: Card?) -> Unit) : CardDataEvent()
}