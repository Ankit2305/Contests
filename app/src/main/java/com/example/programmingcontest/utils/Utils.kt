package com.example.programmingcontest.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.programmingcontest.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_contests.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

fun getResourceIdFromSite(site: String): Int{
    return when(site){
        "CodeForces" -> R.drawable.codeforces
        "CodeForces::Gym" -> R.drawable.codeforces
        "TopCoder" -> R.drawable.topcoder
        "AtCoder" -> R.drawable.atcoder
        "CS Academy" -> R.drawable.csacademy
        "CodeChef" -> R.drawable.codechef
        "HackerRank" -> R.drawable.hackerrank
        "HackerEarth" -> R.drawable.hackerearth
        "LeetCode" -> R.drawable.leetcode
        "Kick Start" -> R.drawable.google
        else -> R.drawable.empty
    }
}

fun getDurationFromSeconds(seconds: String): String{
    //val durationString = ""
    val duration = getLongFromString(seconds)
    return getDurationFromSeconds(duration)
}

fun getDurationFromSeconds(seconds: Long): String{
    //TODO duration gives approx duration make it more precise
    var duration = seconds
    val years: Long = (duration / 31536000)
    duration %= 31536000
    val months: Long = (duration / 2592000)
    duration %= 2592000
    val days: Long = (duration / 86400)
    duration %= 86400
    val hours: Long = (duration / 3600)
    duration %= 3600
    val minutes: Long = (duration / 60)
    var durationString: String = "Duration: "
    if(years > 0)
        durationString += years.toString() + "y "
    if(months > 0)
        durationString += months.toString() + "m "
    if(days > 0)
        durationString += days.toString() + "d "
    if(hours > 0)
        durationString += hours.toString() + "h "
    if(minutes > 0)
        durationString += minutes.toString() + "min"

    return durationString
}


fun getContestsFromJSON(json: String): List<Contest>{
    val gson = GsonBuilder().create()

    val contestRaw = gson.fromJson(json, Array<ContestRaw>::class.java)
    val contests: MutableList<Contest> = emptyArray<Contest>().toMutableList()

    for(item in contestRaw){
        val contest = item.getContest()
        if(contest != null)
            contests.add(contest)
    }

    return contests.toList()
}

fun getLongFromString(value: String): Long{
    var valueInLong: Long = when(val decimalPosition = value.indexOf('.')){
        -1 -> value.toLong()
        else -> value.substring(0 until decimalPosition).toLong()
    }
    return valueInLong;
}