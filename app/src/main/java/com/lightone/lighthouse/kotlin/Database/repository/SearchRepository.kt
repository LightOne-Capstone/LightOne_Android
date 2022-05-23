package com.lightone.lighthouse.kotlin.Database.repository

import androidx.lifecycle.LiveData
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.model.Search
import com.lightone.lighthouse.kotlin.Database.model.UserScrap


class SearchRepository(val dao: SearchDao) {
    fun insert(search: Search) {
        dao.insert(search)
    }

    fun getAll(): LiveData<List<Search>> {
        return dao.getAll()
    }

    fun deleteScrap(id: Int){
        dao.deleteScrap(id)
    }

    fun deleteAll(): Boolean {
        return dao.deleteAll()
    }
}