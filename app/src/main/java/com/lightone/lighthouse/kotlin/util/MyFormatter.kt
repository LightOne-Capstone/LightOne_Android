package com.lightone.lighthouse.kotlin.util

import com.lightone.lighthouse.kotlin.R
import java.text.DecimalFormat

fun priceFormatter(item: Int): String{
    val myFormatter = DecimalFormat("###,###")
    return myFormatter.format(item)
}

fun suggestImg(name: String): Int {
    var img: Int? = null
    when(name){
        "여행" -> img = R.drawable.ic_airplane
        "자동차" -> img = R.drawable.ic_automobile
        "약" -> img = R.drawable.ic_medical
        "영화" -> img = R.drawable.ic_moive
        else -> img = R.drawable.ic_enter
    }
    return img
}