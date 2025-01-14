package ru.clonsaldafon.alphaecosystem.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.clonsaldafon.alphaecosystem.data.db.CardDAO
import ru.clonsaldafon.alphaecosystem.data.db.CardDatabase
import ru.clonsaldafon.alphaecosystem.data.db.MIGRATION_1_2
import ru.clonsaldafon.alphaecosystem.data.repository.CardRepository
import ru.clonsaldafon.alphaecosystem.data.repository.CardRepositoryImpl
import ru.clonsaldafon.alphaecosystem.domain.LoadCardDataUseCase
import ru.clonsaldafon.alphaecosystem.domain.LoadCardDataUseCaseImpl
import ru.clonsaldafon.alphaecosystem.domain.LoadHistoryUseCase
import ru.clonsaldafon.alphaecosystem.domain.LoadHistoryUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppBindsModule {

    @Binds
    fun bindCardRepository(repository: CardRepositoryImpl): CardRepository

    @Binds
    fun bindLoadCardDataUseCase(useCase: LoadCardDataUseCaseImpl): LoadCardDataUseCase

    @Binds
    fun bindLoadHistoryUseCase(useCase: LoadHistoryUseCaseImpl): LoadHistoryUseCase

    companion object {

        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @Provides
        @Singleton
        fun provideDb(context: Context): CardDatabase =
            Room
                .databaseBuilder(
                    context,
                    CardDatabase::class.java,
                    "card.db"
                )
                .addMigrations(MIGRATION_1_2)
                .build()

        @Provides
        @Singleton
        fun provideCardDao(db: CardDatabase): CardDAO =
            db.cardDAO

    }

}