package com.lightone.lighthouse.kotlin.Database.repository

import com.lightone.lighthouse.kotlin.Database.model.UserScrap


class UserScrapRepository(val dao: UserScrapRepository) {
    fun insert(userScrap: UserScrap) {
        dao.insert(userScrap)
    }

    fun deleteScrap(id: Int){
        dao.deleteScrap(id)
    }

    fun getAll(): List<UserScrap> {
        return dao.getAll()
    }
}