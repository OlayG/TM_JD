package com.example.tmobilecodingchallenge.remote

import com.example.tmobilecodingchallenge.models.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface CardService {

    @GET("/test/home")
    suspend fun getResponse(): Response<ResponseDTO>
}