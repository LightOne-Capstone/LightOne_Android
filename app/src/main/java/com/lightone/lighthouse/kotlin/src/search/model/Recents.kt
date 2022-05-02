package com.lightone.lighthouse.kotlin.src.search.model

import com.google.gson.annotations.SerializedName

data class Recents(
    @SerializedName("contents") val contents: String
    )