package com.riyaldi.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository

class FavoriteTvShowViewModel(private val repository: MovieCatalogueRepository): ViewModel() {
    fun getFavTvShows() = repository.getFavoriteTvShows()
}