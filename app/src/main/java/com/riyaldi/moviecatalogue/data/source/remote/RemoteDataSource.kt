package com.riyaldi.moviecatalogue.data.source.remote

import android.util.Log
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.Movie
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.riyaldi.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShow
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShowDetailResponse
import com.riyaldi.moviecatalogue.data.source.remote.response.tv.TvShowResponse
import com.riyaldi.moviecatalogue.network.ApiConfig
import com.riyaldi.moviecatalogue.utils.EspressoIdlingResource
import com.riyaldi.moviecatalogue.utils.NetworkInfo.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovies(API_KEY)
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                callback.onMoviesLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailMovie(callback: LoadDetailMovieCallback, movieId: String) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovieDetail(movieId, API_KEY)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                callback.onDetailMovieLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShows(API_KEY)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                callback.onTvShowsLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, tvShowId: String) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShowDetail(tvShowId, API_KEY)
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                callback.onDetailTvShowLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShow onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies : List<Movie>?)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieLoaded(movieDetail : MovieDetailResponse?)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsLoaded(tvShows : List<TvShow>?)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?)
    }
}