package com.example.search_movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.search_movie.App
import com.example.search_movie.domain.Film
import com.example.search_movie.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel  : ViewModel() {
    val filmsListLiveData:  MutableLiveData<List<Film>> = MutableLiveData()

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getFilms()
        }
    fun getFilms() {
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }

        override fun onFailure() {
            filmsListLiveData.postValue(interactor.getFilmsFromDB())
        }
    })
}

interface ApiCallback {
    fun onSuccess(films: List<Film>)
    fun onFailure()
}
