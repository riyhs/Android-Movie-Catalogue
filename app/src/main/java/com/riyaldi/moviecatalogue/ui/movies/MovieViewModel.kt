package com.riyaldi.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {
    fun getMovies() = movieCatalogueRepository.getMovies()
}