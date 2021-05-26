package com.example.tmobilecodingchallenge.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://private-8ce77c-tmobiletest.apiary-mock.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cardService by lazy { retrofit.create(CardService::class.java) }
}