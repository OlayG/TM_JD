package com.example.tmobilecodingchallenge.repo

import com.example.tmobilecodingchallenge.models.Card
import com.example.tmobilecodingchallenge.remote.CardService
import com.example.tmobilecodingchallenge.remote.RetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform

class CardRepo(
    private val cardService: CardService = RetrofitService.cardService
) {

    suspend fun getCards(): Flow<List<Card>> = flowOf(cardService.getResponse()).transform {
        emit(it.body()?.page?.cards ?: listOf())
    }
}