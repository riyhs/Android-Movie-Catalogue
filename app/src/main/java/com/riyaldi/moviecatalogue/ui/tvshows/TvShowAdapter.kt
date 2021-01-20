package com.riyaldi.moviecatalogue.ui.tvshows

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
import com.riyaldi.moviecatalogue.data.source.local.entity.TvShowEntity
import com.riyaldi.moviecatalogue.databinding.ItemMovieBinding
import com.riyaldi.moviecatalogue.utils.NetworkInfo.IMAGE_URL

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var tvShows = ArrayList<TvShowEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTvShows(tvShows: List<TvShowEntity>) {
        if (tvShows.isNullOrEmpty()) return
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.name
                tvGenre.text = tvShow.voteAverage.toString()

                Glide.with(binding.root.context)
                        .asBitmap()
                        .load(IMAGE_URL + tvShow.posterPath)
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

                itemView.setOnClickListener {

                }

                itemView.setOnClickListener{onItemClickCallback.onItemClicked(tvShow.id.toString())}
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}