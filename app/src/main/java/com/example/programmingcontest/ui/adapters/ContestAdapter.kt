package com.example.programmingcontest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingcontest.core.model.Contest
import com.example.programmingcontest.databinding.ContestListItemBinding
import com.example.programmingcontest.utils.getDateAndFromMillis
import com.example.programmingcontest.utils.getResourceIdFromSite

class ContestAdapter(val contestClickListener: ContestClickListener):
    ListAdapter<Contest, ContestAdapter.ContestViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contest>() {
            override fun areItemsTheSame(oldItem: Contest, newItem: Contest): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Contest, newItem: Contest): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ContestViewHolder(val binding: ContestListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(contest: Contest, contestClickListener: ContestClickListener) {
            binding.apply {
                contestTitleTextView.text = contest.name
                logoImageView.setImageResource(getResourceIdFromSite(contest.site))
                contestDetailsTextView.text = "${getDateAndFromMillis(contest.start_time)} to ${getDateAndFromMillis(contest.end_time)}"
                root.setOnClickListener {
                    contestClickListener.onClick(contest)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        val binding = ContestListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        holder.bind(getContestAt(position), contestClickListener)
    }

    fun getContestAt(position: Int) = getItem(position)

    interface ContestClickListener {
        fun onClick(contest: Contest)
    }
}