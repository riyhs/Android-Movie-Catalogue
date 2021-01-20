package com.riyaldi.moviecatalogue.ui.movies

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
import com.riyaldi.moviecatalogue.databinding.FragmentMovieBinding
import com.riyaldi.moviecatalogue.ui.detail.DetailActivity
import com.riyaldi.moviecatalogue.ui.detail.DetailViewModel.Companion.MOVIE
import com.riyaldi.moviecatalogue.utils.MarginItemDecoration
import com.riyaldi.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                showProgressBar(false)
                movieAdapter.setMovies(movies)
                movieAdapter.setOnItemClickCallback(this)
                movieAdapter.notifyDataSetChanged()
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(fragmentMovieBinding.rvMovies) {
                addItemDecoration(MarginItemDecoration(marginVertical.toInt()))
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)

        context?.startActivity(intent)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.pbMovies.isVisible = state
        fragmentMovieBinding.rvMovies.isInvisible = state
    }

}