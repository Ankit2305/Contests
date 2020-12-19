package com.example.programmingcontest.ui.home

import androidx.lifecycle.LiveData
import com.example.programmingcontest.core.db.ContestDatabase
import com.example.programmingcontest.core.model.Contest
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val contestDatabase: ContestDatabase){
    private val contestDao = contestDatabase.contestDao()

    fun getContestsIn24Hours(): LiveData<List<Contest>> {
        val currentTime = Instant.now().toEpochMilli()
        return contestDao.getContestsIn24Hour(currentTime)
    }
}