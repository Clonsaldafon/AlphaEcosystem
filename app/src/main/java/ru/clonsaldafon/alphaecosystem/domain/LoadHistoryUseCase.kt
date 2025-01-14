package ru.clonsaldafon.alphaecosystem.domain

import ru.clonsaldafon.alphaecosystem.data.db.CardEntity

interface LoadHistoryUseCase {

    suspend operator fun invoke(): Result<List<CardEntity>?>

}