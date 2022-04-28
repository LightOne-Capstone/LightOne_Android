package com.lightone.lighthouse.kotlin.src.home.model

import com.google.gson.annotations.SerializedName

data class Days(
    @SerializedName("days") val days: String,
    @SerializedName("sectors") val sectors: List<Sectors>
    )