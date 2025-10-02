package com.example.search_movie.domain

import com.example.search_movie.data.MainRepository
import com.example.search_movie.data.TmdbApi
import com.example.search_movie.data.entity.Film
import com.example.search_movie.data.entity.TmdbResults
import com.example.search_movie.data.preferenes.PreferenceProvider
import com.example.search_movie.utils.Converter
import com.example.search_movie.view.API
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
      var progressBarState = BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getFilmsFromApi(page: Int) {
            progressBarState.onNext(true)

        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                Completable.fromSingle<List<Film>> {
                    repo.putToDb(list)
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
                progressBarState.onNext(false)
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                    progressBarState.onNext(false)
            }
        })
    }
      fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
      fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): Flow<List<Film>> = repo.getAllFromDB()
}