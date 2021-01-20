package com.riyaldi.moviecatalogue.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.moviecatalogue.databinding.FragmentTvShowBinding
import com.riyaldi.moviecatalogue.ui.detail.DetailActivity
import com.riyaldi.moviecatalogue.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.riyaldi.moviecatalogue.utils.MarginItemDecoration
import com.riyaldi.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowAdapter = TvShowAdapter()

            viewModel.getTvShows().observe(viewLifecycleOwner, {tvShows ->
                showProgressBar(false)
                tvShowAdapter.setTvShows(tvShows)
                tvShowAdapter.setOnItemClickCallback(this)
                tvShowAdapter.notifyDataSetChanged()
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(fragmentTvShowBinding.rvTvShows) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOW)

        context?.startActivity(intent)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentTvShowBinding.pbTvShows.isVisible = state
        fragmentTvShowBinding.rvTvShows.isInvisible = state
    }

}