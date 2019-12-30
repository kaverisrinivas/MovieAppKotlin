package com.example.moviekotlin

import com.google.gson.annotations.SerializedName

class RetroPhoto {

    @SerializedName("albumId")
    private var albumId: Int? = null
    @SerializedName("id")
    private var id: Int? = null
    @SerializedName("title")
    private var title: String? = null
    @SerializedName("url")
    private var url: String? = null
    @SerializedName("thumbnailUrl")
    private var thumbnailUrl: String? = null

    fun getTitle(): String? {
        return title

    }

    fun getThumbnailUrl(): String? {
        return thumbnailUrl
    }


}