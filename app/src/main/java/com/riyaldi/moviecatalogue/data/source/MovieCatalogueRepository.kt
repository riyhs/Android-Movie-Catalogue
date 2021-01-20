package com.riyaldi.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.riyaldi.moviecatalogue.data.source.model.DetailModel
import com.riyaldi.moviecatalogue.data.source.model.MovieModel
import com.riyaldi.moviecatalogue.data.source.model.TvShowModel
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.Movie
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShow
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShowDetailResponse

class MovieCatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieCatalogueDataSource{
    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(remoteData)
            }
    }

    override fun getMovies(): LiveData<List<MovieModel>> {
        val movieResult = MutableLiveData<List<MovieModel>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                val movieList = ArrayList<MovieModel>()
                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = MovieModel(id, title, posterPath, voteAverage)
                            movieList.add(movie)
                        }
                    }
                    movieResult.postValue(movieList)
                }
            }
        })
        return movieResult
    }

    override fun getDetailMovie(movieId: String): LiveData<DetailModel> {
        val movieDetailResult = MutableLiveData<DetailModel>()

        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieLoaded(movieDetail: MovieDetailResponse?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailModel(
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

    override fun getTvShows(): LiveData<List<TvShowModel>> {
        val tvResult = MutableLiveData<List<TvShowModel>>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsLoaded(tvShows: List<TvShow>?) {
                val tvList = ArrayList<TvShowModel>()
                if (tvShows != null) {
                    for (response in tvShows) {
                        with(response) {
                            val tvShow = TvShowModel(id, name, posterPath, voteAverage)
                            tvList.add(tvShow)
                        }
                    }
                    tvResult.postValue(tvList)
                }
            }
        })
        return tvResult
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailModel> {
        val movieDetailResult = MutableLiveData<DetailModel>()

        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?) {
                if (tvShowDetail != null) {
                    with(tvShowDetail) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailModel(
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