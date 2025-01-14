package ru.clonsaldafon.alphaecosystem.data.repository

import retrofit2.HttpException
import ru.clonsaldafon.alphaecosystem.data.db.CardDAO
import ru.clonsaldafon.alphaecosystem.data.db.CardEntity
import ru.clonsaldafon.alphaecosystem.data.model.CardDataResponse
import ru.clonsaldafon.alphaecosystem.data.service.CardService
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val service: CardService,
    private val dao: CardDAO
) : CardRepository {

    override suspend fun loadData(number: Int): Result<CardDataResponse?> {
        kotlin.runCatching {
            service.loadData(number)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    dao.upsertCard(
                        CardEntity(
                            number = number,
                            country = it.body()?.country?.name,
                            coordinates = "${it.body()?.country?.latitude} - " +
                                    "${it.body()?.country?.longitude}",
                            type = it.body()?.type,
                            phone = it.body()?.bank?.phone,
                            website = it.body()?.bank?.url,
                            city = it.body()?.bank?.city
                        )
                    )

                    Result.success(it.body())
                }
                else {
                    Result.failure(HttpException(it))
                }
            },
            onFailure = {
                return Result.failure(it)
            }
        )

    }

    override suspend fun loadHistory(): Result<List<CardEntity>?> {
        kotlin.runCatching {
            dao.getCards()
        }.fold(
            onSuccess = {
                return if (!it.isNullOrEmpty())
                    Result.success(it)
                else
                    Result.failure(Exception("failure"))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

}