package com.riyaldi.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.vo.Resource

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setFilm(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                detailTvShow = movieCatalogueRepository.getDetailTvShow(id.toInt())
            }

            MOVIE -> {
                detailMovie = movieCatalogueRepository.getDetailMovie(id.toInt())
            }
        }
    }

    fun getDetailTvShow() = detailTvShow
    fun getDetailMovie() = detailMovie

}