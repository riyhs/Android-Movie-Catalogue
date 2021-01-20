package com.riyaldi.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.di.Injection
import com.riyaldi.moviecatalogue.ui.detail.DetailViewModel
import com.riyaldi.moviecatalogue.ui.movies.MovieViewModel
import com.riyaldi.moviecatalogue.ui.tvshows.TvShowViewModel

class ViewModelFactory private constructor(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(movieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}