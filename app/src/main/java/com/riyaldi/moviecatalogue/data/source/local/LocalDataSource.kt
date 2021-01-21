package com.riyaldi.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mFilmDao.getMovies()

    fun getMovieById(id: Int): LiveData<MovieEntity> = mFilmDao.getMovieById(id)

    fun getFavMovies(): LiveData<List<MovieEntity>> = mFilmDao.getFavMovies()

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getTvShows()

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = mFilmDao.getTvShowById(id)

    fun getFavTvShows(): LiveData<List<TvShowEntity>> = mFilmDao.getFavTvShows()

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