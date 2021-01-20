package com.riyaldi.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.riyaldi.moviecatalogue.data.source.remote.RemoteDataSource
import com.riyaldi.moviecatalogue.utils.DataDummy
import com.riyaldi.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote)

    private val moviesResponse = DataDummy.getRemoteMovies()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetail = DataDummy.getRemoteDetailMovie()

    private val tvShowResponse = DataDummy.getRemoteTvShows()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.getRemoteDetailTvShow()

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieLoaded(movieDetail)
            null
        }.`when`(remote).getDetailMovie(any(), eq(movieId))

        val movieDetailEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(any(), eq(movieId))
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.id)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsLoaded(tvShowResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.size)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowLoaded(tvShowDetail)
            null
        }.`when`(remote).getDetailTvShow(any(), eq(tvShowId))

        val tvShowDetailEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(any(), eq(tvShowId))
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.id)
    }
}