package com.riyaldi.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.utils.DataDummy
import com.riyaldi.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.getMovies()
    private val dummyTvShow = DataDummy.getTvShows()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(swipeUp())
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(swipeUp())
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavMovies() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailFavMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        onView(withId(R.id.coordinator_layout)).perform(swipeUp())
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavTvShows() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_fav_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailFavTvShow() {
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_fav_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        onView(withId(R.id.coordinator_layout)).perform(swipeUp())
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
    }
}