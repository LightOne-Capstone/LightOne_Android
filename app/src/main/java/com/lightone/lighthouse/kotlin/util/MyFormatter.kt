package com.lightone.lighthouse.kotlin.util

import java.text.DecimalFormat

fun priceFormatter(item: Int): String{
    val myFormatter = DecimalFormat("###,###")
    return myFormatter.format(item)
}