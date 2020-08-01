package com.example.programmingcontest

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.programmingcontest.utils.getContestsFromJSON
import kotlinx.android.synthetic.main.activity_contests.*
import okhttp3.*
import java.io.IOException

class ContestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contests)

        contest_recyclerView.layoutManager = LinearLayoutManager(this)
        val category = intent.getSerializableExtra(FeedViewHolder.CATEGORY_KEY) as ContestCategory

        contest_recyclerView.layoutManager = LinearLayoutManager(this)
        contest_recyclerView.adapter = ContestAdapter(category.contests)

        supportActionBar?.title = category.categoryName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}