package com.riyaldi.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.riyaldi.moviecatalogue.data.source.local.LocalDataSource
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.data.source.remote.ApiResponse
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.Movie
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShow
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShowDetailResponse
import com.riyaldi.moviecatalogue.utils.AppExecutors
import com.riyaldi.moviecatalogue.vo.Resource

class MovieCatalogueRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : MovieCatalogueDataSource {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieCatalogueRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieCatalogueRepository(remoteData, localDataSource, appExecutors)
                }
    }

    override fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
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
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                    remoteDataSource.getDetailMovie(movieId.toString())

            override fun saveCallResult(data: MovieDetailResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val movie = MovieEntity(
                        id = data.id,
                        backdropPath = data.backdropPath,
                        genres = genres.toString(),
                        overview = data.overview,
                        posterPath = data.posterPath,
                        releaseDate = data.releaseDate,
                        runtime = data.runtime,
                        title = data.title,
                        voteAverage = data.voteAverage,
                        isFav = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
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
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                    data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                    remoteDataSource.getDetailTvShow(tvShowId.toString())

            override fun saveCallResult(data: TvShowDetailResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val tvShow = TvShowEntity(
                        id = data.id,
                        backdropPath = data.backdropPath,
                        genres = genres.toString(),
                        overview = data.overview,
                        posterPath = data.posterPath,
                        releaseDate = data.firstAirDate,
                        runtime = data.episodeRunTime.first(),
                        name = data.name,
                        voteAverage = data.voteAverage,
                        isFav = false
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }
}