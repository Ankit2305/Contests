package com.example.programmingcontest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.feed_row.view.*

class FeedAdapter(val contestManager: ContestManager): RecyclerView.Adapter<FeedViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.feed_row, parent, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contestManager.contestsCategories.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val contestCategory = contestManager.contestsCategories[position]

        if(contestCategory.contests.size == 0)
            holder?.itemView.contest_recyclerView.visibility = View.GONE
        else {
            holder?.itemView.noContest_textView.visibility = View.GONE
        }

        if(contestCategory.layoutVertical){
            holder?.itemView.contest_recyclerView.layoutManager = LinearLayoutManager(holder?.itemView.context)
            holder?.itemView.contest_recyclerView.adapter = ContestAdapter(contestCategory.contests)
        } else {
            holder?.itemView.contest_recyclerView.layoutManager = LinearLayoutManager(holder?.itemView.context, LinearLayoutManager.HORIZONTAL, false)
            holder?.itemView.contest_recyclerView.adapter = ContestHorizontalAdapter(contestCategory.contests)
        }

        holder?.itemView.category_textView.text = contestCategory.categoryName

        holder?.category = contestCategory
    }

}

class FeedViewHolder(view: View, var category: ContestCategory? = null): RecyclerView.ViewHolder(view){
    companion object{
        val CATEGORY_KEY = "CATEGORY"
    }

    init{
        view.category_textView.setOnClickListener{
            val intent = Intent(it.context, ContestsActivity::class.java)
            intent.putExtra(CATEGORY_KEY, category)
            it.context.startActivity(intent)
        }
    }
}