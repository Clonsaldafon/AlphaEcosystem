package ru.clonsaldafon.alphaecosystem.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.clonsaldafon.alphaecosystem.data.db.CardEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val number: Int?,
    val country: String?,
    val coordinates: String?,
    val type: String?,
    val phone: String?,
    val website: String?,
    val city: String?
) {

    companion object {
        const val TABLE_NAME = "cards"
    }

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE cards ADD COLUMN number INTEGER")
    }
}
