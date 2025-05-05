package com.example.search_movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setFilmsDetails()
    }

    private fun setFilmsDetails() {
        val film = intent.extras?.get("film") as Film

        details_toolbar.title = film.title
        details_poster.setImageResource(film.poster)
        details_description.text = film.description
    }
}{
}