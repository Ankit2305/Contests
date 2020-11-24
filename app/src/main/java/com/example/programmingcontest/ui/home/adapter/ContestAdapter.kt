package com.example.programmingcontest.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingcontest.core.model.Contest
import com.example.programmingcontest.databinding.ContestListItemBinding

class ContestAdapter():
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

        fun bind(contest: Contest) {
            binding.apply {
                contestTitleTextView.text = contest.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        val binding = ContestListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        holder.bind(getContestAt(position))
    }

    fun getContestAt(position: Int) = getItem(position)
}