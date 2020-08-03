package com.example.programmingcontest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.programmingcontest.utils.getContestsFromJSON
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    //Customize Feeds here
    val feedOrder = listOf(
        listOf(IN24HOURS, true),
        listOf(ONGOING, false),
        listOf(ALLUPCOMING, false),
        listOf(SHORT, false),
        listOf(LONG, false),
        listOf(CODECHEF, false),
        listOf(CODEFORCES, false),
        listOf(HACKERRANK, false),
        listOf(HACKEREARTH, false),
        listOf(LEETCODE, false),
        listOf(KICKSTART, false),
        listOf(ATCODER, false),
        listOf(TOPCODER, false),
        listOf(CSACADEMY, false)
    )
    val contestManager = ContestManager(feedOrder = feedOrder)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("contests_data", Context.MODE_PRIVATE)

        feed_recyclerView.layoutManager = LinearLayoutManager(this)

        loadOfflineJSON()
        fetchJSON()
    }

    private fun loadOfflineJSON(){
        val json = sharedPreferences.getString("contests", "[]")

        val contests = getContestsFromJSON(json!!)

        contestManager.init(contests)

        runOnUiThread {
            feed_recyclerView.adapter = FeedAdapter(contestManager)
        }
    }

    private fun fetchJSON(){
        val url = "https://www.kontests.net/api/v1/all"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                sharedPreferences.edit().putString("contests", body).apply()

                val contests = getContestsFromJSON(body!!)

                contestManager.init(contests)

                runOnUiThread {
                    feed_recyclerView.adapter = FeedAdapter(contestManager)
                }
            }
        })
    }
}