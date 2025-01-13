package ru.clonsaldafon.alphaecosystem.presentation.view

sealed class CardDataEvent {
    data class OnNumberChanged(val value: String) : CardDataEvent()
    data class OnSubmit(val onComplete: (card: Card?) -> Unit) : CardDataEvent()
}