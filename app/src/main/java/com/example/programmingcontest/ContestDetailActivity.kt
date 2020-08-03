package com.example.programmingcontest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.MenuItem
import androidx.core.app.NavUtils
import com.example.programmingcontest.utils.getDurationFromSeconds
import kotlinx.android.synthetic.main.activity_contest_detail.*
import java.text.SimpleDateFormat
import java.util.*

class ContestDetailActivity : AppCompatActivity() {
    lateinit var name: String
    lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contest_detail)

        val logoResource = intent.getIntExtra(ContestViewHolder.LOGO_RESOURCE_KEY, -1)
        title = intent.getStringExtra(ContestViewHolder.SITE_KEY)!!
        name = intent.getStringExtra(ContestViewHolder.NAME_KEY)!!
        val url = intent.getStringExtra(ContestViewHolder.URL_KEY)
        val startTime = intent.getLongExtra(ContestViewHolder.START_TIME_KEY, 0)
        val endTime = intent.getLongExtra(ContestViewHolder.END_TIME_KEY, 0)
        val duration = intent.getLongExtra(ContestViewHolder.DURATION_KEY, 0)
        val in_24_hours = intent.getBooleanExtra(ContestViewHolder.IN_24_HOURS_KEY, false)
        val ongoing = intent.getBooleanExtra(ContestViewHolder.ONGOING_KEY, false)

        supportActionBar?.title = title
        logo_imageView.setImageResource(logoResource)
        name_textView.text = name
        view_Button.text = "View on $title"

        val durationString = getDurationFromSeconds(duration!!)
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy-hh:mm a", Locale.US)

        val start = simpleDateFormat.format(Date(startTime))
        val end = simpleDateFormat.format(Date(endTime))

        val startDate = start.split('-')
        val endDate = end.split('-')


        start_time_date_textView.text = startDate[0]
        start_time_time_textView.text = startDate[1]
        end_time_date_textView.text = endDate[0]
        end_time_time_textView.text = endDate[1]
        duration_textView.text = durationString.substring(10)

        view_Button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        reminder_Button.setOnClickListener {
            AddEvent(startTime, endTime, url!!)
        }

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun AddEvent(startTime: Long, endTime: Long, url: String){
        val title = name

        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
            .putExtra(CalendarContract.Events.TITLE, title)
            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
            .putExtra(CalendarContract.Events.EVENT_LOCATION, url)

        startActivity(intent);
    }
}