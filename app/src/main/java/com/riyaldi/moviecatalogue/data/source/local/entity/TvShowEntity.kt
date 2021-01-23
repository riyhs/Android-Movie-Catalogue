package com.riyaldi.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entities")
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int,

        @ColumnInfo(name = "backdropPath")
        var backdropPath: String,

        @ColumnInfo(name = "genres")
        var genres: String,

        @ColumnInfo(name = "overview")
        var overview: String,

        @ColumnInfo(name = "posterPath")
        var posterPath: String,

        @ColumnInfo(name = "releaseDate")
        var releaseDate: String,

        @ColumnInfo(name = "runtime")
        var runtime: Int,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "voteAverage")
        var voteAverage: Double,

        @ColumnInfo(name = "isFav")
        var isFav: Boolean = false
)