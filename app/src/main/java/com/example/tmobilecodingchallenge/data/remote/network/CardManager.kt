package com.example.tmobilecodingchallenge.data.remote.network

import com.example.tmobilecodingchallenge.data.models.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET

class CardManager {
    private val service: CardService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(CardService::class.java)
    }

    suspend fun getResponse(): Response<ResponseDTO> {
        return service.getResponse()
    }

    interface CardService {
        @GET("/test/home")
        suspend fun getResponse(): Response<ResponseDTO>
    }

}