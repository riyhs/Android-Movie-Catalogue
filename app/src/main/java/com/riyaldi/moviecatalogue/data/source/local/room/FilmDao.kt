package com.riyaldi.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface FilmDao {
//    @Query("SELECT * FROM movie_entities")
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isFav = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

//    @Query("SELECT * FROM tv_show_entities")
    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isFav = 1")
    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}