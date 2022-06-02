package com.lightone.lighthouse.kotlin.src.search.model

import com.google.gson.annotations.SerializedName
import com.lightone.lighthouse.kotlin.R
import com.lightone.lighthouse.kotlin.src.home.model.Sectors

data class Tags(
    var Idx: Int = 1,
    @SerializedName("category") val category: String,
    @SerializedName("count") val count: String
    )

//{
//    "category": "개인 서비스",
//    "count": "1"
//}