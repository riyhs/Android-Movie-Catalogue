package com.riyaldi.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.vo.Resource

interface MovieCatalogueDataSource {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>
}