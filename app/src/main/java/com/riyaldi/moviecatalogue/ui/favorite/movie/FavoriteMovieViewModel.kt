package com.riyaldi.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository

class FavoriteMovieViewModel(private val repository: MovieCatalogueRepository): ViewModel() {
    fun getFavMovies() = repository.getFavoriteMovies()
}