package com.example.programmingcontest.ui.listcontest

import com.example.programmingcontest.core.db.ContestDatabase
import com.example.programmingcontest.core.model.Contest
import java.time.Instant
import javax.inject.Inject

class ListContestRepository @Inject constructor(database: ContestDatabase) {
    val contestDao = database.contestDao()

    fun getContest(type: ListContestTypes, value: String): List<Contest> {
        return when(type) {
            ListContestTypes.SITE -> getContestOfSite(value)
            ListContestTypes.CATEGORY -> getContestOfCategory(value)
            else -> emptyList()
        }
    }

    fun getContestOfSite(site: String): List<Contest> {
        val currentTime = Instant.now().toEpochMilli()
        return contestDao.getContestsOfSite(site, currentTime)
    }

    fun getContestOfCategory(category: String): List<Contest> {
        val currentTime = Instant.now().toEpochMilli()
        return when(category) {
            "Long" -> contestDao.getLongContest()
            "Short" -> contestDao.getShortContest()
            "Ongoing" -> contestDao.getOngoingContest(currentTime)
            "Upcoming" -> contestDao.getUpcomingContest(currentTime)
            else -> listOf()
        }
    }
}