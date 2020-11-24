package com.example.programmingcontest.ui.explore

import com.example.programmingcontest.core.db.ContestDatabase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ExploreRepository @Inject constructor(private val database: ContestDatabase) {
    val contestDao = database.contestDao()
    val siteDao = database.siteDao()

    fun getContests() = runBlocking {
        contestDao.getContests()
    }

    fun getSites() = runBlocking {
        siteDao.getSites()
    }

}