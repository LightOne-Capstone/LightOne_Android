package com.lightone.lighthouse.kotlin.src.home.model

import com.google.gson.annotations.SerializedName

data class Sectors(
    @SerializedName("idx") val idx: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("nowPrice") val nowPrice: Int,
    @SerializedName("taegetPrice") val taegetPrice: Int,
    @SerializedName("status") val status: String,
    @SerializedName("stockCompany") val stockCompany: String
    )