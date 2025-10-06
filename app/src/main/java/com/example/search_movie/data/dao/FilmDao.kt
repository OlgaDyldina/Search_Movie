package com.example.search_movie.data.dao

import android.database.Observable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.search_movie.data.entity.Film

@Dao
interface FilmDao{
    @Query("SELECT * FROM cached_films")
    fun getCachedFilms(): Observable<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Film>)
}