package ru.clonsaldafon.alphaecosystem.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CardDAO {

    @Upsert
    suspend fun upsertCard(card: CardEntity)

    @Query("SELECT * FROM cards")
    suspend fun getCards(): List<CardEntity>?

}
