package com.riyaldi.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.riyaldi.moviecatalogue.data.source.local.entity.DetailEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity

interface MovieCatalogueDataSource {
    fun getMovies(): LiveData<List<MovieEntity>>
    fun getDetailMovie(movieId: String): LiveData<DetailEntity>
    fun getTvShows(): LiveData<List<TvShowEntity>>
    fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity>
}