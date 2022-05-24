package com.lightone.lighthouse.kotlin.Database.repository

import androidx.lifecycle.LiveData
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.model.UserSearch


class SearchRepository(val dao: SearchRepository) {
    fun insert(search: UserSearch) {
        dao.insert(search)
    }

    fun getAll(): LiveData<List<UserSearch>> {
        return dao.getAll()
    }

    fun deleteSearch(idx: String){
        dao.deleteSearch(idx)
    }

    fun deleteAll() {
        dao.deleteAll()
    }
}