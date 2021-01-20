package com.riyaldi.moviecatalogue.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.AppBarLayout
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.data.source.local.entity.DetailEntity
import com.riyaldi.moviecatalogue.databinding.ActivityDetailBinding
import com.riyaldi.moviecatalogue.utils.NetworkInfo.IMAGE_URL
import com.riyaldi.moviecatalogue.viewmodel.ViewModelFactory
import kotlin.math.abs


class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var detailBinding: ActivityDetailBinding
    private val percentageToShowImage = 20
    private var mMaxScrollSize = 0
    private var mIsImageHidden = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.hide()

        showProgressBar(true)

        detailBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
        detailBinding.appbar.addOnOffsetChangedListener(this)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_FILM)
            val dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataId != null && dataCategory != null) {
                viewModel.setFilm(dataId, dataCategory)
                viewModel.getDataDetail().observe(this, { detail ->
                    populateDataDetail(detail)
                })
            }
        }

    }

    private fun showProgressBar(state: Boolean) {
        detailBinding.progressBar.isVisible = state
        detailBinding.appbar.isInvisible = state
        detailBinding.nestedScrollView.isInvisible = state
    }

    private fun populateDataDetail(data: DetailEntity) {
        val genre = data.genres.toString().replace("[", "").replace("]", "")

        val genreDurationText = resources.getString(R.string.genre_duration_text, genre, data.runtime.toString())

        detailBinding.tvDetailGenreDuration.text = genreDurationText
        detailBinding.collapsing.title = data.title
        detailBinding.tvDetailOverview.text = data.overview

        Glide.with(this)
                .asBitmap()
                .load(IMAGE_URL + data.posterPath)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        detailBinding.ivDetail.setImageBitmap(resource)
                        setColorByPalette(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })

        Glide.with(this)
                .load(IMAGE_URL + data.backdropPath)
                .into(detailBinding.ivBackdrop)

        detailBinding.ivDetail.tag = data.posterPath
        detailBinding.ivBackdrop.tag = data.backdropPath

        showProgressBar(false)
    }

    private fun setColorByPalette(poster: Bitmap) {
        Palette.from(poster).generate { palette ->
            val defValue = resources.getColor(R.color.dark, theme)
            detailBinding.cardDetail.setCardBackgroundColor(
                    palette?.getDarkMutedColor(defValue) ?: defValue
            )
            detailBinding.collapsing.setContentScrimColor(
                    palette?.getDarkMutedColor(defValue) ?: defValue
            )
            window.statusBarColor = palette?.getDarkMutedColor(defValue) ?: defValue
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout!!.totalScrollRange

        val currentScrollPercentage: Int = (abs(verticalOffset) * 100 / mMaxScrollSize)

        if (currentScrollPercentage >= percentageToShowImage) {
            if (!mIsImageHidden) {
                mIsImageHidden = true
            }
        }

        if (currentScrollPercentage < percentageToShowImage) {
            if (mIsImageHidden) {
                mIsImageHidden = false
            }
        }
    }
}