package com.riyaldi.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.ui.movies.MovieFragment
import com.riyaldi.moviecatalogue.ui.tvshows.TvShowFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setViewPager()
    }

    private fun setViewPager() {
        val fragmentList = listOf(MovieFragment(), TvShowFragment())
        val tabTitle = listOf(resources.getString(R.string.movie), resources.getString(R.string.tv_show))

        viewpager.adapter = ViewpagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout2, viewpager){tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}