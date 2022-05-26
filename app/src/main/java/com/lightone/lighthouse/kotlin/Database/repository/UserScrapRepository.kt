package com.lightone.lighthouse.kotlin.Database.repository

import androidx.lifecycle.LiveData
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap


class UserScrapRepository(val dao: UserScrapRepository) {
    fun insert(userScrap: UserScrap) {
        dao.insert(userScrap)
    }

    fun deleteScrap(idx: Int){
        dao.deleteScrap(idx)
    }

    fun getAll(): LiveData<List<UserScrap>> {
        return dao.getAll()
    }
}