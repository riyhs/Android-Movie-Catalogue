package com.riyaldi.moviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.getMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieCatalogueRepository.getMovies()).thenReturn(movies)
        val movie = viewModel.getMovies().value
        verify(movieCatalogueRepository).getMovies()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}