package com.example.tmobilecodingchallenge.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmobilecodingchallenge.data.models.ResponseDTO
import com.example.tmobilecodingchallenge.data.repos.CardRepo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    private var _response = MutableLiveData<ResponseDTO>()
    val response get() = _response

    private val cardRepo: CardRepo by lazy { CardRepo() }

    init {
        getResponse()
    }

    fun getResponse(){

        viewModelScope.launch {
            try {
                val getResponse = async { cardRepo.getResponse() }

                response.value = getResponse.await().body()
            } catch (e: Exception){
                Log.e("TAG", e.toString())
            }
        }

    }

}