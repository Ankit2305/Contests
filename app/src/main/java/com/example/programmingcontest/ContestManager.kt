package com.example.programmingcontest

import java.io.Serializable

class ContestManager(val feedOrder: List<List<Any>>) {
    val categoryNames = listOf(
        "In 24 Hours",
        "All Upcoming",
        "Ongoing",
        "CodeChef Upcoming",
        "CodeForces Upcoming",
        "HackerRank Upcoming",
        "HackerEarth Upcoming",
        "AtCoder Upcoming",
        "TopCoder Upcoming",
        "Kick Start Upcoming",
        "CS Academy Upcoming",
        "Short",
        "Long",
        "LeetCode Upcoming"
    )

    var contestsCategories: List<ContestCategory> = listOf()

    fun init(contests: List<Contest>){
        var mutableContests = mutableListOf<ContestCategory>()

        var feeds = listOf(
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>(),
            mutableListOf<Contest>()
        )

        for(contest in contests){
            if(contest.ongoing)
                feeds[ONGOING].add(contest)
            else {
                feeds[ALLUPCOMING].add(contest)

                when(contest.site){
                    "CodeChef" -> feeds[CODECHEF].add(contest)
                    "CodeForces" -> feeds[CODEFORCES].add(contest)
                    "HackerRank" -> feeds[HACKERRANK].add(contest)
                    "HackerEarth" -> feeds[HACKEREARTH].add(contest)
                    "AtCoder" -> feeds[ATCODER].add(contest)
                    "TopCoder" -> feeds[TOPCODER].add(contest)
                    "Kick Start" -> feeds[KICKSTART].add(contest)
                    "CS Academy" -> feeds[CSACADEMY].add(contest)
                    "LeetCode" -> feeds[LEETCODE].add(contest)
                }

                if(contest.duration <= 18000){
                    feeds[SHORT].add(contest)
                } else {
                    feeds[LONG].add(contest)
                }
            }

            if(contest.in_24_hours)
                feeds[IN24HOURS].add(contest)

        }

        feeds[ONGOING].sortWith(compareBy{
            it.end_time
        })

        for(currentFeed in feedOrder){
            val index = currentFeed[0] as Int
            val vertical = currentFeed[1] as Boolean
            mutableContests.add(
                ContestCategory(
                    categoryNames[index],
                    feeds[index],
                    vertical
                )
            )
        }

        contestsCategories = mutableContests.toList()
    }
}

class ContestCategory(
    val categoryName: String,
    var contests: List<Contest>,
    val layoutVertical: Boolean
): Serializable