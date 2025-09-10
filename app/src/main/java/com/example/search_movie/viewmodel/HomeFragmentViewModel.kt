package com.example.search_movie.viewmodel


import androidx.lifecycle.ViewModel
import com.example.search_movie.App
import com.example.search_movie.data.entity.Film
import com.example.search_movie.domain.Interactor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeFragmentViewModel  : ViewModel() {

    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Flow<List<Film>>
    val showProgressBar: Channel<Boolean>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1)
    }
}
