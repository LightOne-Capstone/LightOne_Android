package com.lightone.lighthouse.kotlin.Database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lightone.lighthouse.kotlin.Database.model.UserSearch

@Dao
interface SearchDao {
    @Query("SELECT * FROM search")
    fun getAll(): LiveData<List<UserSearch>>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(search: UserSearch)

    @Query("DELETE from search")
    fun deleteAll()

    @Query("DELETE FROM search WHERE idx = :idx")
    fun deleteScrap(idx: String)
}