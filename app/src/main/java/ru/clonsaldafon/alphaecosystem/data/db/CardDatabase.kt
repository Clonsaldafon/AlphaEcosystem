package ru.clonsaldafon.alphaecosystem.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CardEntity::class
    ],
    version = 3
)
abstract class CardDatabase : RoomDatabase() {

    abstract val cardDAO: CardDAO

}