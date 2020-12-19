package com.example.programmingcontest.core.model

data class ContestsAppVersion(
    val versionNumber: String
) {
    companion object {
        const val LATEST_VERSION_URL = ""

        fun currentVersion() = ContestsAppVersion("2.0.0")
    }
}