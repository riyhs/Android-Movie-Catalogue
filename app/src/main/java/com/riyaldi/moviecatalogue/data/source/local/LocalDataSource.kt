package com.riyaldi.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.data.source.local.room.FilmDao
import com.riyaldi.moviecatalogue.utils.SortUtils
import com.riyaldi.moviecatalogue.utils.SortUtils.MOVIE_ENTITIES
import com.riyaldi.moviecatalogue.utils.SortUtils.TV_SHOW_ENTITIES

class LocalDataSource(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> = mFilmDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getMovieById(id: Int): LiveData<MovieEntity> = mFilmDao.getMovieById(id)

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = mFilmDao.getFavMovies()

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> = mFilmDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITIES))

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = mFilmDao.getTvShowById(id)

    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity> = mFilmDao.getFavTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mFilmDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mFilmDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mFilmDao.updateMovie(movie)
    }

    fun insertTvShows(tvShows: List<TvShowEntity>) = mFilmDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFav = newState
        mFilmDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFav = newState
        mFilmDao.updateTvShow(tvShow)
    }
}