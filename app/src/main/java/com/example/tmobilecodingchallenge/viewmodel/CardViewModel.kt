package com.example.tmobilecodingchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmobilecodingchallenge.models.Card
import com.example.tmobilecodingchallenge.repo.CardRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CardViewModel(private val repo: CardRepo) : ViewModel() {

    private val cards = MutableLiveData<List<Card>>()
    val cardsLD: LiveData<List<Card>>
        get() = cards

    init {
        viewModelScope.launch(Dispatchers.IO) { repo.getCards().collect { cards.postValue(it) } }
    }
}
