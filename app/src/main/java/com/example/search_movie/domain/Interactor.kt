package com.example.search_movie.domain

import com.example.search_movie.data.MainRepository
import com.example.search_movie.data.TmdbApi
import com.example.search_movie.data.entity.Film
import com.example.search_movie.data.entity.TmdbResultsDto
import com.example.search_movie.data.preferenes.PreferenceProvider
import com.example.search_movie.utils.Converter
import com.example.search_movie.view.API
import com.example.search_movie.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                list.forEach {
                    repo.putToDb(list)
                }
                callback.onSuccess()
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
               callback.onFailure()
            }
        })
    }
      fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
      fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): List<Film> = repo.getAllFromDB()
}