package com.example.programmingcontest

import java.io.Serializable
import java.time.Instant

//name: "ProjectEuler+",
//url: "https://hackerrank.com/contests/projecteuler",
//start_time: "2014-07-07T15:38:00.000Z",
//end_time: "2024-07-30T18:30:00.000Z",
//duration: "317616720.0",
//site: "HackerRank",
//in_24_hours: "No",
//status: "CODING"

class ContestRaw(
    val name: String,
    val url: String,
    val start_time: String,
    val end_time: String,
    val duration: String,
    val site: String,
    val in_24_hours: String,
    val status: String
){
    fun getContest(): Contest?{
        val start = Instant.parse(start_time).toEpochMilli()
        val end = Instant.parse(end_time).toEpochMilli()
        val currentDateAndTime = Instant.now().toEpochMilli()

        if(end < currentDateAndTime)
            return null
        return Contest(name, url, start, end, duration, site,
            in_24_hours == "Yes", status == "CODING"
        )
    }
}

class Contest(
    val name: String,
    val url: String,
    val start_time: Long,
    val end_time: Long,
    val duration: String,
    val site: String,
    val in_24_hours: Boolean,
    val ongoing: Boolean
): Serializable

