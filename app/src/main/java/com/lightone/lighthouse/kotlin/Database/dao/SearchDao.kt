package com.lightone.lighthouse.kotlin.Database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lightone.lighthouse.kotlin.Database.model.Search
import com.lightone.lighthouse.kotlin.Database.model.UserScrap

@Dao
interface SearchDao {
    @Query("SELECT * FROM search")
    fun getAll(): LiveData<List<Search>>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(search: Search)

    @Query("DELETE from search")
    fun deleteAll(): Boolean

    @Query("DELETE FROM search WHERE id = :id")
    fun deleteScrap(id: Int)
}