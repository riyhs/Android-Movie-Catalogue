package com.riyaldi.moviecatalogue.ui.movies

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.data.source.local.entity.MovieEntity
import com.riyaldi.moviecatalogue.databinding.ItemMovieBinding
import com.riyaldi.moviecatalogue.utils.NetworkInfo.IMAGE_URL

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var movies = ArrayList<MovieEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<MovieEntity>){
        if (movies.isNullOrEmpty()) return
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvGenre.text = movie.voteAverage.toString()

                Glide.with(itemView.context)
                        .asBitmap()
                        .load(IMAGE_URL + movie.posterPath)
                        .transform(RoundedCorners(28))
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                ivPoster.setImageBitmap(resource)

                                Palette.from(resource).generate { palette ->
                                    val defValue = itemView.resources.getColor(R.color.dark, itemView.context.theme)
                                    cardItem.setCardBackgroundColor(palette?.getDarkMutedColor(defValue) ?: defValue)
                                }
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {
                            }
                        })

                itemView.setOnClickListener {onItemClickCallback.onItemClicked(movie.id.toString())}
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}