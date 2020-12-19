package com.example.programmingcontest.ui.site

import com.example.programmingcontest.core.db.ContestDatabase
import com.example.programmingcontest.core.model.Contest
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SiteRepository @Inject constructor(private val database: ContestDatabase) {
    val siteDao = database.siteDao()

    fun getSites() = runBlocking {
        siteDao.getSites()
    }
}