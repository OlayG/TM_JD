package com.example.tmobilecodingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmobilecodingchallenge.repo.CardRepo

class CardViewModelFactory(private val repo: CardRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CardViewModel(repo) as T
    }
}
