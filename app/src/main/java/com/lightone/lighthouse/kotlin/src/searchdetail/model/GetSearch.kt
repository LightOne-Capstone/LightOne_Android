package com.lightone.lighthouse.kotlin.src.searchdetail.model

import com.google.gson.annotations.SerializedName

data class GetSearch(
    @SerializedName("date") val date: String,
    @SerializedName("lowPrice") val lowPrice: Int,
    @SerializedName("highPrice") val highPrice: Int,
    @SerializedName("terminalPrice") val terminalPrice: Int
    )

//{
//    "date": "2022-05-18",
//    "lowPrice": 39300,
//    "highPrice": 40300,
//    "terminalPrice": 39400
//}