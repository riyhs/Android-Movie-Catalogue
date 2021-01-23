package com.riyaldi.moviecatalogue.ui.movies

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.databinding.FragmentMovieBinding
import com.riyaldi.moviecatalogue.ui.detail.DetailActivity
import com.riyaldi.moviecatalogue.ui.detail.DetailViewModel.Companion.MOVIE
import com.riyaldi.moviecatalogue.ui.home.HomeActivity
import com.riyaldi.moviecatalogue.utils.MarginItemDecoration
import com.riyaldi.moviecatalogue.viewmodel.ViewModelFactory
import com.riyaldi.moviecatalogue.vo.Status

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            (activity as HomeActivity).setActionBarTitle("Movies List")

            showProgressBar(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when(movies.status) {
                        Status.LOADING -> showProgressBar(true)
                        Status.SUCCESS -> {
                            showProgressBar(false)
                            movieAdapter.submitList(movies.data)
                            movieAdapter.setOnItemClickCallback(this)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.bookmark_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_bookmark -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.action_bookmark3 -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.action_bookmark4 -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.pbMovies.isVisible = state
        fragmentMovieBinding.rvMovies.isInvisible = state
    }

}