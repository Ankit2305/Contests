package com.example.programmingcontest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingcontest.utils.getDurationFromSeconds
import com.example.programmingcontest.utils.getResourceIdFromSite
import kotlinx.android.synthetic.main.contest_horizontal_row.view.*
import kotlinx.android.synthetic.main.contest_row.view.*
import kotlinx.android.synthetic.main.contest_row.view.logo_imageView
import java.text.SimpleDateFormat
import java.util.*

class ContestAdapter(val contests: List<Contest>): RecyclerView.Adapter<ContestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.contest_row, parent, false)
        return ContestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contests.size
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        val contest = contests[position]
        //TODO refactor date conversion

        val logoResource: Int = getResourceIdFromSite(contest.site)
        val duration = getDurationFromSeconds(contest.duration)

        val simpleDateFormat = SimpleDateFormat("dd MMM yyyy-HH:mm a", Locale.US)

        val start = simpleDateFormat.format(Date(contest.start_time))
        val end = simpleDateFormat.format(Date(contest.end_time))

        val startDate = start.split('-')
        val endDate = end.split('-')

        holder?.itemView.contestTitle_textView.text = contest.name
        holder?.itemView.logo_imageView.setImageResource(logoResource)
        holder?.itemView.startDate_textView.text = startDate[0]
        holder?.itemView.startTime_textView.text = startDate[1]
        holder?.itemView.endDate_textView.text = endDate[0]
        holder?.itemView.endTime_textView.text = endDate[1]
        holder?.itemView.duration_textView.text = duration

        holder?.contest = contest
        holder?.logoResource = logoResource
    }

}

class ContestHorizontalAdapter(val contests: List<Contest>): RecyclerView.Adapter<ContestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.contest_horizontal_row, parent, false)
        return ContestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contests.size
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        val contest = contests[position]
        val logoResource = getResourceIdFromSite(contest.site)

        val name = if(contest.name.length > 30) contest.name.substring(0..27) + "..." else contest.name

        holder?.itemView.logo_imageView.setImageResource(logoResource)
        holder?.itemView.name_textView.text = name

        holder?.contest = contest
        holder?.logoResource = logoResource
    }

}

class ContestViewHolder(
    view: View,
    var contest: Contest? = null,
    var logoResource: Int? = null
): RecyclerView.ViewHolder(view){

    companion object{
        val SITE_KEY = "SITE"
        val LOGO_RESOURCE_KEY = "LOGO_RESOURCE"
        val NAME_KEY = "NAME"
        val URL_KEY = "URL"
        val START_TIME_KEY = "START_TIME"
        val END_TIME_KEY = "END_TIME"
        val DURATION_KEY = "DURATION"
        val IN_24_HOURS_KEY = "IN_24_HOURS"
        val ONGOING_KEY = "ONGOING"
    }

    init{
        view.setOnClickListener {
            val intent = Intent(it.context, ContestDetailActivity::class.java)
            intent.putExtra(SITE_KEY, contest?.site)
            intent.putExtra(LOGO_RESOURCE_KEY, logoResource)
            intent.putExtra(NAME_KEY, contest?.name)
            intent.putExtra(URL_KEY, contest?.url)
            intent.putExtra(START_TIME_KEY, contest?.start_time)
            intent.putExtra(END_TIME_KEY, contest?.end_time)
            intent.putExtra(DURATION_KEY, contest?.duration)
            intent.putExtra(IN_24_HOURS_KEY, contest?.in_24_hours)
            intent.putExtra(ONGOING_KEY, contest?.ongoing)
            it.context.startActivity(intent)
        }
    }
}