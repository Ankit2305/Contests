package com.example.programmingcontest.core.model

import com.example.programmingcontest.utils.getLongFromString
import java.time.Instant

data class ContestResponse (
    val name: String,
    val url: String,
    val start_time: String,
    val end_time: String,
    val duration: String,
    val site: String,
    val in_24_hours: String,
    val status: String
) {

    fun getContest(): Contest?{
        val start = Instant.parse(start_time).toEpochMilli()
        val end = Instant.parse(end_time).toEpochMilli()
        val currentDateAndTime = Instant.now().toEpochMilli()

        if(end < currentDateAndTime)
            return null

        return Contest (
            name,
            url,
            start,
            end,
            getLongFromString(duration),
            site,
            (start > currentDateAndTime && start - currentDateAndTime <= 86400000),
            start <= currentDateAndTime
        )
    }

}