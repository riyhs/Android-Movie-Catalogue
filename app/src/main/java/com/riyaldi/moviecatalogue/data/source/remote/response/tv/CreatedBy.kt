package com.riyaldi.moviecatalogue.data.source.remote.response.tv


import com.google.gson.annotations.SerializedName

data class CreatedBy(
        @SerializedName("id")
        val id: Int,
        @SerializedName("credit_id")
        val creditId: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("gender")
        val gender: Int,
        @SerializedName("profile_path")
        val profilePath: String?
)