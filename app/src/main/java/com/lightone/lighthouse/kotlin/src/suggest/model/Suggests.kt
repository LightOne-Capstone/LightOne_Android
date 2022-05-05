package com.lightone.lighthouse.kotlin.src.suggest.model

import com.google.gson.annotations.SerializedName
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Sectors

data class Suggests(
    @SerializedName("img") val img: Int,
    @SerializedName("name") val name: String,
    @SerializedName("count") val count: Int
    )