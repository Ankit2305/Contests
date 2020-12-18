package com.example.programmingcontest.ui

import android.util.Log
import com.example.programmingcontest.core.api.KontestsApi
import com.example.programmingcontest.core.db.ContestDatabase
import com.example.programmingcontest.core.model.Site
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val kontestsApi: KontestsApi,
    private val contestDatabase: ContestDatabase
) {
    val contestDao = contestDatabase.contestDao()
    val siteDao = contestDatabase.siteDao()


    fun updateDB() {
        GlobalScope.launch {
            try {
                updateContests()
                updateSites()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun updateSites() {
        val sitesResponse = kontestsApi.getSites()

        for(item in sitesResponse) {
            val site = Site.parseSite(item)
            if(site != null && siteDao.searchSite(site.name).isEmpty()) {
                siteDao.insert(site)
            }
        }
    }

    private suspend fun updateContests() {
        val contestResponse = kontestsApi.getContests()

        for(item in contestResponse) {
            val contest = item.getContest()
            if(contest != null && contestDao.searchContest(contest.name).isEmpty())
                contestDao.insert(contest)
        }
        val contests = contestDao.searchContests()
        val currentTime = Instant.now().toEpochMilli()
        for(contest in contests) {
            if(contest.end_time < currentTime)
                contestDao.delete(contest)
        }
    }
}