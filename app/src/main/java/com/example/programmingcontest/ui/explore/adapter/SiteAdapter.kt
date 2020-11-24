package com.example.programmingcontest.ui.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingcontest.core.model.Contest
import com.example.programmingcontest.core.model.Site
import com.example.programmingcontest.databinding.ContestListItemBinding

class SiteAdapter():
    ListAdapter<Site, SiteAdapter.SiteViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Site>() {
            override fun areItemsTheSame(oldItem: Site, newItem: Site): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Site, newItem: Site): Boolean {
                return oldItem == newItem
            }
        }
    }

    class SiteViewHolder(val binding: ContestListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(site: Site) {
            binding.apply {
                contestTitleTextView.text = site.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val binding = ContestListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SiteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        holder.bind(getSiteAt(position))
    }

    fun getSiteAt(position: Int) = getItem(position)
}