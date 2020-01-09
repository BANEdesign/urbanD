package com.bryonnabaines.urbandictionaryapp.api

import com.google.gson.annotations.SerializedName


/**
 * created by bryonnabaines on 2020-01-07
 */

data class Word(
    @SerializedName("definition")
    val definition: String? = null,

    @SerializedName("permalink")
    val permalink: String? = null,

    @SerializedName("thumbs_up")
    val thumbsUp: Int,

    @SerializedName("sound_urls")
    val soundUrls: List<String>? = listOf(),

    @SerializedName("author")
    val author: String? = null,

    @SerializedName("word")
    val word: String? = null,

    @SerializedName("written_on")
    val writtenOn: String? = null,

    @SerializedName("thumbs_down")
    val thumbsDown: Int

)