package com.lightone.lighthouse.kotlin.Database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lightone.lighthouse.kotlin.Database.model.UserScrap

@Dao
interface UserScrapDao {
    @Query("SELECT * FROM userscrap")
    fun getAll(): LiveData<List<UserScrap>>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: UserScrap)

    @Query("DELETE from userscrap")
    fun deleteAll()

    @Query("DELETE FROM userscrap WHERE idx = :idx")
    fun deleteScrap(idx: Int)
}