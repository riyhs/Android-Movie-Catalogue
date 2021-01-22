package com.riyaldi.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.riyaldi.moviecatalogue.data.source.local.LocalDataSource
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource
import com.riyaldi.moviecatalogue.utils.AppExecutors
import com.riyaldi.moviecatalogue.utils.DataDummy
import com.riyaldi.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.getRemoteMovies()
    private val movieId = moviesResponse[0].id
    private val movieDetail = DataDummy.getRemoteDetailMovie()

    private val tvShowResponse = DataDummy.getRemoteTvShows()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetail = DataDummy.getRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.getMovies()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovies())
        verify(local).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.getDetailMovie()
        `when`(local.getMovieById(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.getTvShows()
        `when`(local.getAllTvShows()).thenReturn(dummyTvShows)

        val tvShowEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShows())
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.getDetailTvShow()
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyDetail)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.id)
    }
}