package ru.clonsaldafon.alphaecosystem.data.service

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path
import ru.clonsaldafon.alphaecosystem.data.model.CardDataResponse

interface CardService {

    @POST("{number}")
    suspend fun loadData(
        @Path("number") number: Int
    ): Response<CardDataResponse>

}