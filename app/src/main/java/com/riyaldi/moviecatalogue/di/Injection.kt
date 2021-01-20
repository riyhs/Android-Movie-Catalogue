package com.riyaldi.moviecatalogue.di

import android.content.Context
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}