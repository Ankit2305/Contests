package com.example.programmingcontest.core.api

import com.example.programmingcontest.core.model.ContestResponse
import retrofit2.http.GET

interface KontestsApi {

    companion object {
        const val BASE_URL = "https://www.kontests.net/api/v1/"
    }

    @GET("all")
    suspend fun getContests(): List<ContestResponse>

    @GET("sites")
    suspend fun getSites(): List<List<String>>

}