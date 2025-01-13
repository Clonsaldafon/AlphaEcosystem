package ru.clonsaldafon.alphaecosystem.data.repository

import ru.clonsaldafon.alphaecosystem.data.model.CardDataResponse

interface CardRepository {

    suspend fun loadData(number: Int): Result<CardDataResponse?>

}