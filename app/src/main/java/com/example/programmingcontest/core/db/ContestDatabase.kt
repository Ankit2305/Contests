package com.example.programmingcontest.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.programmingcontest.core.model.Contest
import com.example.programmingcontest.core.model.Site

@Database(entities = [Contest::class, Site::class], version = 2)
abstract class ContestDatabase : RoomDatabase() {
    abstract fun contestDao(): ContestDao
    abstract fun siteDao(): SiteDao

    companion object {
        var instance: ContestDatabase? = null

        fun getContestDataBase(context: Context): ContestDatabase {
            if (instance == null) {
                synchronized(ContestDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContestDatabase::class.java,
                        "contest_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}