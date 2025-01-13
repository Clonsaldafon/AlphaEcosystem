package ru.clonsaldafon.alphaecosystem.presentation.view

data class CardDataUiState(
    val number: String = "",
    val card: Card? = null,
    val isLoading: Boolean = false
) {

    fun reset() = copy(
        number = "",
        card = null,
        isLoading = false
    )

}
