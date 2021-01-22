package com.riyaldi.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.riyaldi.moviecatalogue.ui.tvshows.TvShowAdapter
import com.riyaldi.moviecatalogue.utils.MarginItemDecoration
import com.riyaldi.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentMovieFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            val adapter = TvShowAdapter()
            viewModel.getFavTvShows().observe(viewLifecycleOwner, { favTvShow ->
                if (favTvShow != null) {
                    adapter.submitList(favTvShow)
                    adapter.notifyDataSetChanged()
                }
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(fragmentTvShowBinding.rvFavMovies) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

}