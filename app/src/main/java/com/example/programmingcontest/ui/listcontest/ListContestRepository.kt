package com.example.programmingcontest.ui.listcontest

import com.example.programmingcontest.core.db.ContestDatabase
import com.example.programmingcontest.core.model.Contest
import javax.inject.Inject

class ListContestRepository @Inject constructor(database: ContestDatabase) {
    val contestDao = database.contestDao()

    fun getContest(type: Int, value: String): List<Contest> {
        return when(type) {
            0 -> getContestOfSite(value)
            else -> emptyList()
        }
    }

    fun getContestOfSite(site: String): List<Contest> {
        return contestDao.getContestsOfSite(site)
    }
}