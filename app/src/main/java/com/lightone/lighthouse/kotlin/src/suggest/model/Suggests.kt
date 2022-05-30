package com.lightone.lighthouse.kotlin.src.suggest.model

import com.google.gson.annotations.SerializedName
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Sectors

data class Suggests(
    @SerializedName("category") val category: String,
    @SerializedName("count") val count: String
    )

//{
//    "category": "개인 서비스",
//    "count": "1"
//}