package com.riyaldi.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.riyaldi.moviecatalogue.data.source.model.DetailModel
import com.riyaldi.moviecatalogue.data.source.model.MovieModel
import com.riyaldi.moviecatalogue.data.source.model.TvShowModel

interface MovieCatalogueDataSource {
    fun getMovies(): LiveData<List<MovieModel>>
    fun getDetailMovie(movieId: String): LiveData<DetailModel>
    fun getTvShows(): LiveData<List<TvShowModel>>
    fun getDetailTvShow(tvShowId: String): LiveData<DetailModel>
}