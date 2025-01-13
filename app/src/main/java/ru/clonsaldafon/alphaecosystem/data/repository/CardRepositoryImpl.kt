package ru.clonsaldafon.alphaecosystem.data.repository

import retrofit2.HttpException
import ru.clonsaldafon.alphaecosystem.data.model.CardDataResponse
import ru.clonsaldafon.alphaecosystem.data.service.CardService
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val service: CardService
) : CardRepository {

    override suspend fun loadData(number: Int): Result<CardDataResponse?> {
        kotlin.runCatching {
            service.loadData(number)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful)
                    Result.success(it.body())
                else Result.failure(HttpException(it))
            },
            onFailure = {
                return Result.failure(it)
            }
        )

    }

}