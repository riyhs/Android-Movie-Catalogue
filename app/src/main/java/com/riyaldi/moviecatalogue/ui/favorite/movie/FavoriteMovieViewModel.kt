package com.riyaldi.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val repository: MovieCatalogueRepository) : ViewModel() {
    fun getFavMovies() = repository.getFavoriteMovies()

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFav
        repository.setFavoriteMovie(movieEntity, newState)
    }
}