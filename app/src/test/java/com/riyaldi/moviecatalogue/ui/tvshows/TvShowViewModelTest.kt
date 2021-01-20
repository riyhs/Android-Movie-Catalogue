package com.riyaldi.moviecatalogue.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.riyaldi.moviecatalogue.data.source.MovieCatalogueRepository
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = DataDummy.getTvShows()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(movieCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows().value
        verify(movieCatalogueRepository).getTvShows()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}