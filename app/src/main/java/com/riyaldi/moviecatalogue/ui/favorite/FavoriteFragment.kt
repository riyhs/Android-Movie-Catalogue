package com.riyaldi.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.ui.favorite.movie.MovieFavoriteFragment
import com.riyaldi.moviecatalogue.ui.favorite.tvshow.TvShowFavoriteFragment
import com.riyaldi.moviecatalogue.ui.home.ViewpagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setViewPager()
        }
    }

    private fun setViewPager() {
        val fragmentList = listOf(MovieFavoriteFragment(), TvShowFavoriteFragment())
        val tabTitle = listOf(resources.getString(R.string.movie), resources.getString(R.string.tv_show))

        viewpager.adapter = ViewpagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout2, viewpager){tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}