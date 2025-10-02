package com.example.search_movie.data

import android.database.Observable
import androidx.lifecycle.LiveData
import com.example.search_movie.data.dao.FilmDao
import com.example.search_movie.data.entity.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
            filmDao.insertAll(films)
    }

    fun getAllFromDB(): Observable<List<Film>> = filmDao.getCachedFilms()
}