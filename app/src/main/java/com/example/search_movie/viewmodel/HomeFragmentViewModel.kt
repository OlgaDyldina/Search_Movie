package com.example.search_movie.viewmodel

import android.arch.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.search_movie.App
import com.example.search_movie.domain.Film
import com.example.search_movie.domain.Interactor

class HomeFragmentViewModel  : ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private val interactor: Interactor = App.instance.interactor

    init {
        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}
