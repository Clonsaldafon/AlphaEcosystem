package ru.clonsaldafon.alphaecosystem.domain

import ru.clonsaldafon.alphaecosystem.data.db.CardEntity
import ru.clonsaldafon.alphaecosystem.data.repository.CardRepository
import javax.inject.Inject

class LoadHistoryUseCaseImpl @Inject constructor(
    private val repository: CardRepository
) : LoadHistoryUseCase {

    override suspend fun invoke(): Result<List<CardEntity>?> =
        repository.loadHistory()

}