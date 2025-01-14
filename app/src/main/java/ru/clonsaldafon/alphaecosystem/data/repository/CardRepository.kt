package ru.clonsaldafon.alphaecosystem.data.repository

import ru.clonsaldafon.alphaecosystem.data.db.CardEntity
import ru.clonsaldafon.alphaecosystem.data.model.CardDataResponse

interface CardRepository {

    suspend fun loadData(number: Int): Result<CardDataResponse?>

    suspend fun loadHistory(): Result<List<CardEntity>?>

}