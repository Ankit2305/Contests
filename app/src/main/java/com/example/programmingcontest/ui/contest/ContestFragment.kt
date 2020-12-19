package com.example.programmingcontest.ui.contest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.programmingcontest.R
import com.example.programmingcontest.utils.getDateFromMillis
import com.example.programmingcontest.utils.getDurationFromMillis
import com.example.programmingcontest.utils.getResourceIdFromSite
import com.example.programmingcontest.utils.getTimeFromMillis
import kotlinx.android.synthetic.main.fragment_contest.*

class ContestFragment : Fragment(R.layout.fragment_contest) {
    private val args: ContestFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showContest()
    }

    private fun showContest() {
        val contest = args.contest

        contestTitleTextView.text = contest.name
        startDateTextView.text = getDateFromMillis(contest.start_time)
        startTimeTextView.text = getTimeFromMillis(contest.start_time)
        endDateTextView.text = getDateFromMillis(contest.end_time)
        endTimeTextView.text = getTimeFromMillis(contest.end_time)
        durationTextView.text = getDurationFromMillis(contest.duration)

        logoImageView.setImageResource(getResourceIdFromSite(contest.site))

        reminderButton.setOnClickListener {
            AddEvent(contest.name, contest.start_time, contest.end_time, contest.url)
        }

        viewButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(contest.url))
            startActivity(intent)
        }
    }

    private fun AddEvent(title: String, startTime: Long, endTime: Long, url: String){
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