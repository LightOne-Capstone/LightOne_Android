package com.lightone.lighthouse.kotlin.Database.repository

import androidx.lifecycle.LiveData
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap


class UserScrapRepository(val dao: UserScrapDao) {
    fun insert(userScrap: UserScrap) {
        dao.insert(userScrap)
    }

    fun deleteScrap(id: Int){
        dao.deleteScrap(id)
    }

    fun getAll(): LiveData<List<UserScrap>> {
        return dao.getAll()
    }
}