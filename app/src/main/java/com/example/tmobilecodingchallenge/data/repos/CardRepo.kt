package com.example.tmobilecodingchallenge.data.repos

import com.example.tmobilecodingchallenge.data.models.ResponseDTO
import com.example.tmobilecodingchallenge.data.remote.network.CardManager
import retrofit2.Response

class CardRepo {

    suspend fun getResponse(): Response<ResponseDTO> {
        return CardManager().getResponse()
    }

}