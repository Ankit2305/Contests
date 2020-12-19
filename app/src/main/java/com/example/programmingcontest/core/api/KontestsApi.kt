package com.example.programmingcontest.core.api

import com.example.programmingcontest.core.model.ContestResponse
import com.example.programmingcontest.core.model.ContestsAppVersion
import retrofit2.http.GET
import retrofit2.http.Url

interface KontestsApi {

    companion object {
        const val BASE_URL = "https://www.kontests.net/api/v1/"
    }

    @GET("all")
    suspend fun getContests(): List<ContestResponse>

    @GET("sites")
    suspend fun getSites(): List<List<String>>

    @GET
    suspend fun getLatestVersion(@Url url: String): ContestsAppVersion
}