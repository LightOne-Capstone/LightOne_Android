package com.lightone.lighthouse.kotlin.src.search.model

import com.google.gson.annotations.SerializedName

data class Recents(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
    )

// var id: Int,
//    var name: String