package com.example.programmingcontest.ui

import com.example.programmingcontest.core.api.KontestsApi
import com.example.programmingcontest.core.db.ContestDatabase
import com.example.programmingcontest.core.model.ContestsAppVersion
import com.example.programmingcontest.core.model.Site
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    fun getLatestVersion() = runBlocking {
        try {
            fetchLatestVersion()
        } catch (e: Exception) {
            ContestsAppVersion.currentVersion()
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

    private suspend fun fetchLatestVersion(): ContestsAppVersion {
        return kontestsApi.getLatestVersion(ContestsAppVersion.LATEST_VERSION_URL)
    }
}