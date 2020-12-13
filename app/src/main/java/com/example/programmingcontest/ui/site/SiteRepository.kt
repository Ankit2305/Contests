package com.example.programmingcontest.ui.site

import com.example.programmingcontest.core.db.ContestDatabase
import com.example.programmingcontest.core.model.Contest
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SiteRepository @Inject constructor(private val database: ContestDatabase) {
    val contestDao = database.contestDao()
    val siteDao = database.siteDao()

    fun getContests() = runBlocking {
        contestDao.getContests()
    }

    fun getSites() = runBlocking {
        siteDao.getSites()
    }

    fun getContestOfSite(site: String): List<Contest> {
        return contestDao.getContestsOfSite(site)
    }

}