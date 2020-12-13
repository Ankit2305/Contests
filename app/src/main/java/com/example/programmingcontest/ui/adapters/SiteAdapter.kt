package com.example.programmingcontest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingcontest.core.model.Site
import com.example.programmingcontest.databinding.SiteListItemBinding
import com.example.programmingcontest.utils.getResourceIdFromSite

class SiteAdapter(private val siteClickListener: SiteClickListener):
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

    class SiteViewHolder(val binding: SiteListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(site: Site, siteClickListener: SiteClickListener) {
            binding.apply {
                siteTextView.text = site.name
                logoImageView.setImageResource(getResourceIdFromSite(site.name))
                root.setOnClickListener {
                    siteClickListener.onClick(site.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val binding = SiteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SiteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        holder.bind(getSiteAt(position), siteClickListener)
    }

    fun getSiteAt(position: Int) = getItem(position)

    interface SiteClickListener {
        fun onClick(site: String)
    }
}