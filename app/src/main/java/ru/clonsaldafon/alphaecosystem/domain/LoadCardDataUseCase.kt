package ru.clonsaldafon.alphaecosystem.domain

import ru.clonsaldafon.alphaecosystem.data.model.CardDataResponse

interface LoadCardDataUseCase {

    suspend operator fun invoke(number: Int): Result<CardDataResponse?>

}