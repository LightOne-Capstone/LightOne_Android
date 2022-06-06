package com.lightone.lighthouse.kotlin.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userscrap")
class UserScrap(
    val company_id: String,
    val company_name: String,
    val suggestion: String,
    val currentPrice: Int,
    val targetPrice: Int,
    val writerCompany: String,
    val writer: String,
    val date: String
){
    @PrimaryKey(autoGenerate = true) var idx: Int = 0
}
