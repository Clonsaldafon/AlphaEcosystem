package ru.clonsaldafon.alphaecosystem.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.clonsaldafon.alphaecosystem.data.repository.CardRepository
import ru.clonsaldafon.alphaecosystem.data.repository.CardRepositoryImpl
import ru.clonsaldafon.alphaecosystem.domain.LoadCardDataUseCase
import ru.clonsaldafon.alphaecosystem.domain.LoadCardDataUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface AppBindsModule {

    @Binds
    fun bindCardRepository(repository: CardRepositoryImpl): CardRepository

    @Binds
    fun bindLoadCardDataUseCase(useCase: LoadCardDataUseCaseImpl): LoadCardDataUseCase

}