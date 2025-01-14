package ru.clonsaldafon.alphaecosystem.data.model

data class ApiException(
    override val message: String,
    val code: Int
) : Throwable()
