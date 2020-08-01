package com.example.programmingcontest

import java.io.Serializable

class ContestManager() {
    var contestsCategories: List<ContestCategory> = listOf()

    fun init(contests: List<Contest>){
        var mutableContests = mutableListOf<ContestCategory>()

        var in24Hours = mutableListOf<Contest>()
        var allUpcoming = mutableListOf<Contest>()
        var ongoing = mutableListOf<Contest>()
        var codechef = mutableListOf<Contest>()
        var codeforces = mutableListOf<Contest>()
        var hackerrank = mutableListOf<Contest>()
        var hackerearth = mutableListOf<Contest>()
        var atcoder = mutableListOf<Contest>()
        var topcoder = mutableListOf<Contest>()
        var kickstart = mutableListOf<Contest>()


        for(contest in contests){
            if(contest.ongoing)
                ongoing.add(contest)
            else {
                allUpcoming.add(contest)

                when(contest.site){
                    "CodeChef" -> codechef.add(contest)
                    "CodeForces" -> codeforces.add(contest)
                    "HackerRank" -> hackerrank.add(contest)
                    "HackerEarth" -> hackerearth.add(contest)
                    "AtCoder" -> atcoder.add(contest)
                    "TopCoder" -> topcoder.add(contest)
                    "Kick Start" -> kickstart.add(contest)
                }
            }

            if(contest.in_24_hours)
                in24Hours.add(contest)

        }

        ongoing.sortWith(compareBy{
            it.end_time
        })

        mutableContests.add(
            ContestCategory(
                "In 24 Hours",
                in24Hours,
                true
            )
        )
        mutableContests.add(
            ContestCategory(
                "Ongoing",
                ongoing,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "All Upcoming",
                allUpcoming,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "CodeChef Upcoming",
                codechef,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "CodeForces Upcoming",
                codeforces,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "HackerRank Upcoming",
                hackerrank,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "HackerEarth Upcoming",
                hackerearth,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "AtCoder Upcoming",
                atcoder,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "Kick Start Upcoming",
                kickstart,
                false
            )
        )
        mutableContests.add(
            ContestCategory(
                "TopCoder Upcoming",
                topcoder,
                false
            )
        )

        contestsCategories = mutableContests.toList()
    }
}

class ContestCategory(
    val categoryName: String,
    var contests: List<Contest>,
    val layoutVertical: Boolean
): Serializable