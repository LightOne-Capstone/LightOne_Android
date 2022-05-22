package com.lightone.lighthouse.kotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lightone.lighthouse.kotlin.Database.dao.UserScrapDao
import com.lightone.lighthouse.kotlin.Database.model.UserScrap

@Database(entities = [UserScrap::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userscrapDao(): UserScrapDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "lighthouse.db")
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