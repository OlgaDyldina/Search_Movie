package com.example.search_movie.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.remote_module.entity.ApiConstants
import com.example.search_movie.data.entity.Film
import com.example.search_movie.databinding.FilmItemBinding

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val filmItemBinding = FilmItemBinding.bind(itemView)
    private val title = filmItemBinding.title
    private val poster = filmItemBinding.poster
    private val description = filmItemBinding.description
    private val ratingDonut = filmItemBinding.ratingDonut

      fun bind(film: Film) {
        title.text = film.title
          Glide.with(itemView)
              .load(com.example.remote_module.entity.ApiConstants.IMAGES_URL + "w342" + film.poster)
              .centerCrop()
              .into(poster)
        description.text = film.description
        ratingDonut.setProgress((film.rating * 10).toInt())
    }
}