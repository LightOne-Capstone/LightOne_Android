package com.lightone.lighthouse.kotlin.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search")
class Search(
    var id: Int,
    var name: String
){
    @PrimaryKey(autoGenerate = true) var idx: Int = 0
}
