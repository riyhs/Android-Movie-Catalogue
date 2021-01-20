package com.riyaldi.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.riyaldi.moviecatalogue.data.source.local.entity.DetailEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.Movie
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShow
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShowDetailResponse

class FakeMovieCatalogueRepository (private val remoteDataSource: RemoteDataSource) : MovieCatalogueDataSource {
    override fun getMovies(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()
                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = MovieEntity(id, title, posterPath, voteAverage)
                            movieList.add(movie)
                        }
                    }
                    movieResult.postValue(movieList)
                }
            }
        })
        return movieResult
    }

    override fun getDetailMovie(movieId: String): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieLoaded(movieDetail: MovieDetailResponse?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailEntity(
                                backdropPath = backdropPath,
                                genres = listGenres,
                                id = id,
                                overview = overview,
                                posterPath = posterPath,
                                releaseDate = releaseDate,
                                runtime = runtime,
                                title = title,
                                voteAverage = voteAverage
                        )
                        movieDetailResult.postValue(detailMovie)
                    }
                }
            }
        }, movieId)
        return movieDetailResult
    }

    override fun getTvShows(): LiveData<List<TvShowEntity>> {
        val tvResult = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsLoaded(tvShows: List<TvShow>?) {
                val tvList = ArrayList<TvShowEntity>()
                if (tvShows != null) {
                    for (response in tvShows) {
                        with(response) {
                            val tvShow = TvShowEntity(id, name, posterPath, voteAverage)
                            tvList.add(tvShow)
                        }
                    }
                    tvResult.postValue(tvList)
                }
            }
        })
        return tvResult
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity> {
        val movieDetailResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?) {
                if (tvShowDetail != null) {
                    with(tvShowDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailEntity(
                                backdropPath = backdropPath,
                                genres = listGenres,
                                id = id,
                                overview = overview,
                                posterPath = posterPath,
                                releaseDate = firstAirDate,
                                runtime = episodeRunTime.average().toInt(),
                                title = name,
                                voteAverage = voteAverage
                        )
                        movieDetailResult.postValue(detailMovie)
                    }
                }
            }
        }, tvShowId)
        return movieDetailResult
    }
}