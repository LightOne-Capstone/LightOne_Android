package com.lightone.lighthouse.kotlin.src.detail.model

import com.google.gson.annotations.SerializedName

data class Chart(
    @SerializedName("date") val date: String,
    @SerializedName("lowPrice") val lowPrice: Int,
    @SerializedName("highPrice") val highPrice: Int,
    @SerializedName("terminalPrice") val terminalPrice: Int
    )

//{
//    "date": "2022-05-20",
//    "lowPrice": 0,
//    "highPrice": 0,
//    "terminalPrice": 2445
//}