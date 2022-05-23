package com.lightone.lighthouse.kotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap

@Database(entities = [UserScrap::class], version = 1)
abstract class UserScrapDatabase: RoomDatabase() {
    abstract fun userscrapDao(): UserScrapDao

    companion object {
        private var INSTANCE: UserScrapDatabase? = null

        fun getInstance(context: Context): UserScrapDatabase? {
            if (INSTANCE == null) {
                synchronized(UserScrapDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserScrapDatabase::class.java, "lighthouse.db")
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