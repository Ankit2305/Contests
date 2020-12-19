package com.example.programmingcontest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingcontest.core.model.Category
import com.example.programmingcontest.databinding.SiteListItemBinding
import com.example.programmingcontest.utils.getResourceIdFromCategory

class CategoryAdapter(private val categoryClickListener: CategoryClickListener):
    ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }

    class CategoryViewHolder(val binding: SiteListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category, categoryClickListener: CategoryClickListener) {
            binding.apply {
                siteTextView.text = category.name
                logoImageView.setImageResource(getResourceIdFromCategory(category.name))
                root.setOnClickListener {
                    categoryClickListener.onClick(category.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = SiteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getCategoryAt(position), categoryClickListener)
    }

    fun getCategoryAt(position: Int) = getItem(position)

    interface CategoryClickListener {
        fun onClick(category: String)
    }
}