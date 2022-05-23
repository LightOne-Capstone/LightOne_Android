package com.lightone.lighthouse.kotlin.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search")
class UserSearch(
    var contents: String
){
    @PrimaryKey(autoGenerate = true) var idx: Int = 0
}
