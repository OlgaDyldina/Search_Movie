package com.example.search_movie.domain

import android.media.audiofx.DynamicsProcessing
import com.example.search_movie.data.MainRepository
import com.example.search_movie.data.TmdbApi
import com.example.search_movie.data.entity.Film
import com.example.search_movie.data.entity.TmdbResultsDto
import com.example.search_movie.data.preferenes.PreferenceProvider
import com.example.search_movie.utils.Converter
import com.example.search_movie.view.API
import com.example.search_movie.viewmodel.HomeFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState = Channel<Boolean>(Channel.CONFLATED)

    fun getFilmsFromApi(page: Int) {
        scope.launch {
            progressBarState.send(true)
        }

        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                scope.launch {
                    repo.putToDb(list)
                    progressBarState.send(false)
                }
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                scope.launch {
                    progressBarState.send(false)
                }
            }
        })
    }
      fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
      fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): Flow<List<Film>> = repo.getAllFromDB()
}