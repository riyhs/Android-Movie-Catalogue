package com.riyaldi.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.riyaldi.moviecatalogue.data.source.local.LocalDataSource
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.data.source.remote.ApiResponse
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.Movie
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShow
import com.riyaldi.moviecatalogue.utils.AppExecutors
import com.riyaldi.moviecatalogue.vo.Resource

class MovieCatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogueDataSource{

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(remoteData, localDataSource, appExecutors)
            }
    }

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<MovieEntity>> = localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        genres = "",
                        overview = response.overview,
                        posterPath = response.posterPath,
                        releaseDate = response.releaseDate,
                        runtime = 0,
                        title = response.title,
                        voteAverage = response.voteAverage,
                        isFav = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val resultMovie = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        genres = "",
                        overview = response.overview,
                        posterPath = response.posterPath,
                        releaseDate = response.releaseDate,
                        runtime = 0,
                        title = response.title,
                        voteAverage = response.voteAverage,
                        isFav = false
                    )
                    resultMovie.add(movie)
                }
                localDataSource.insertMovies(resultMovie)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<TvShowEntity>> = localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val movieList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val movie = TvShowEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        genres = "",
                        overview = response.overview,
                        posterPath = response.posterPath,
                        releaseDate = response.firstAirDate,
                        runtime = 0,
                        name = response.name,
                        voteAverage = response.voteAverage,
                        isFav = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertTvShows(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, List<TvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                    remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val resultTvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    val movie = TvShowEntity(
                            id = response.id,
                            backdropPath = response.backdropPath,
                            genres = "",
                            overview = response.overview,
                            posterPath = response.posterPath,
                            releaseDate = response.firstAirDate,
                            runtime = 0,
                            name = response.name,
                            voteAverage = response.voteAverage,
                            isFav = false
                    )
                    resultTvShow.add(movie)
                }
                localDataSource.insertTvShows(resultTvShow)
            }
        }.asLiveData()
    }
}