package com.example.search_movie.domain

import com.example.search_movie.data.MainRepository

class Interactor (val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}