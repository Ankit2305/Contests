package com.example.programmingcontest.core.model

data class ContestsAppVersion(
    val versionNumber: String
) {
    companion object {
        const val LATEST_VERSION_URL = "https://raw.githubusercontent.com/Ankit2305/Contests/master/version.json"

        fun currentVersion() = ContestsAppVersion("2.0")
    }
}