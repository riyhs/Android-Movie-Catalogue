package com.riyaldi.moviecatalogue.di

import android.content.Context
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.data.source.local.LocalDataSource
import com.riyaldi.moviecatalogue.data.source.local.room.FilmDatabase
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource
import com.riyaldi.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {
        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()
        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}