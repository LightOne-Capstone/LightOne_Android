package com.lightone.lighthouse.kotlin.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userscrap")
class UserScrap(
    var id: Int,
    var company: Boolean
){
    @PrimaryKey(autoGenerate = true) var idx: Int = 0
}
