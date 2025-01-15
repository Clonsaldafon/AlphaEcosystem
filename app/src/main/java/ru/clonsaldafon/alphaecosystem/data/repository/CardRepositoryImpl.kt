package ru.clonsaldafon.alphaecosystem.data.repository

import ru.clonsaldafon.alphaecosystem.data.db.CardDAO
import ru.clonsaldafon.alphaecosystem.data.db.CardEntity
import ru.clonsaldafon.alphaecosystem.data.model.ApiException
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
                            scheme = it.body()?.scheme,
                            type = it.body()?.type,
                            bankName = it.body()?.bank?.name,
                            city = it.body()?.bank?.city,
                            website = it.body()?.bank?.url,
                            phone = it.body()?.bank?.phone,
                            brand = it.body()?.brand,
                            country = "${it.body()?.country?.emoji} " +
                                    "${it.body()?.country?.name}",
                            latitude = it.body()?.country?.latitude,
                            longitude = it.body()?.country?.longitude
                        )
                    )

                    Result.success(it.body())
                }
                else {
                    Result.failure(ApiException(it.message(), it.code()))
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