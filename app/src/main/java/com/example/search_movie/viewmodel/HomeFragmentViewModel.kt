package com.example.search_movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.search_movie.App
import com.example.search_movie.domain.Film
import com.example.search_movie.domain.Interactor

class HomeFragmentViewModel  : ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private val interactor: Interactor = App.instance.interactor

    interactor.getFilmsFromApi(1, object : ApiCallback {
        override fun onSuccess(films: List<Film>) {
            filmsListLiveData.postValue(films)
        }

        override fun onFailure() {
        }
    })
}

interface ApiCallback {
    fun onSuccess(films: List<Film>)
    fun onFailure()
}
