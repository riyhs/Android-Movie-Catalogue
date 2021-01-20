package com.riyaldi.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {
    fun getTvShows() = movieCatalogueRepository.getTvShows()
}