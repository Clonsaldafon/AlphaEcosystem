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
    val scheme: String?,
    val type: String?,
    val bankName: String?,
    val city: String?,
    val website: String?,
    val phone: String?,
    val brand: String?,
    val country: String?,
    val latitude: Int?,
    val longitude: Int?
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

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE cards ADD COLUMN scheme TEXT")
        db.execSQL("ALTER TABLE cards ADD COLUMN bankName TEXT")
        db.execSQL("ALTER TABLE cards ADD COLUMN brand TEXT")
        db.execSQL("ALTER TABLE cards ADD COLUMN latitude INT")
        db.execSQL("ALTER TABLE cards ADD COLUMN longitude INT")
        db.execSQL("ALTER TABLE cards DROP COLUMN coordinates")
    }
}