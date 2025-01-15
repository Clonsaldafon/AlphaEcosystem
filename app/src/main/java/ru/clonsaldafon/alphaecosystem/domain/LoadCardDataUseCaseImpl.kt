package ru.clonsaldafon.alphaecosystem.domain

import ru.clonsaldafon.alphaecosystem.data.model.CardDataResponse
import ru.clonsaldafon.alphaecosystem.data.repository.CardRepository
import javax.inject.Inject

class LoadCardDataUseCaseImpl @Inject constructor(
    private val repository: CardRepository
) : LoadCardDataUseCase {

    override suspend fun invoke(number: Int): Result<CardDataResponse?> =
        repository.loadData(number)

}