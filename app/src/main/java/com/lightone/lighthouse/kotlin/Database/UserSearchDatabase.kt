package com.lightone.lighthouse.kotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lightone.lighthouse.kotlin.Database.dao.SearchDao
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap
import com.lightone.lighthouse.kotlin.Database.model.UserSearch

@Database(entities = [UserSearch::class], version = 1)
abstract class UserSearchDatabase: RoomDatabase() {
    abstract fun usersearchDao(): SearchDao

    companion object {
        private var INSTANCE: UserSearchDatabase? = null

        fun getInstance(context: Context): UserSearchDatabase? {
            if (INSTANCE == null) {
                synchronized(UserSearchDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserSearchDatabase::class.java, "lighthouse.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}