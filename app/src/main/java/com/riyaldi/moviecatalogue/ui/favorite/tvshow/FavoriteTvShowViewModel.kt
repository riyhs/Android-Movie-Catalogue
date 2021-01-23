package com.riyaldi.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val repository: MovieCatalogueRepository): ViewModel() {
    fun getFavTvShows() = repository.getFavoriteTvShows()

    fun setFavTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFav
        repository.setFavoriteTvShow(tvShowEntity, newState)
    }
}