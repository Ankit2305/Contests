package com.example.programmingcontest.utils

import com.example.programmingcontest.*
import java.text.SimpleDateFormat
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

fun getResourceIdFromCategory(category: String): Int {
    return when(category) {
        "Long" -> R.drawable.ic_long
        "Short" -> R.drawable.ic_short
        "Ongoing" -> R.drawable.ic_ongoing
        "Upcoming" -> R.drawable.ic_upcoming
        else -> R.drawable.empty
    }
}

fun getDateAndFromMillis(millis: Long): String {
    val simpleDateFormat = SimpleDateFormat("HH:mm a, dd MMM", Locale.US)
    return simpleDateFormat.format(Date(millis))
}

fun getDateFromMillis(millis: Long): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    return simpleDateFormat.format(Date(millis))
}

fun getTimeFromMillis(millis: Long): String {
    val simpleDateFormat = SimpleDateFormat("HH:mm a", Locale.US)
    return simpleDateFormat.format(Date(millis))
}


fun getDurationFromSeconds(seconds: String): String{
    //val durationString = ""
    val duration = getLongFromString(seconds)
    return getDurationFromMillis(duration)
}

fun getDurationFromMillis(millis: Long): String{
    //TODO duration gives approx duration make it more precise
    var duration = millis
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

fun getLongFromString(value: String): Long{
    var valueInLong: Long = when(val decimalPosition = value.indexOf('.')){
        -1 -> value.toLong()
        else -> value.substring(0 until decimalPosition).toLong()
    }
    return valueInLong;
}