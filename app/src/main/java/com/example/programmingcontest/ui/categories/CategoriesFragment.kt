package com.example.programmingcontest.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.programmingcontest.R
import com.example.programmingcontest.core.model.Category
import com.example.programmingcontest.databinding.FragmentCategoriesBinding
import com.example.programmingcontest.ui.adapters.CategoryAdapter
import com.example.programmingcontest.ui.listcontest.ListContestTypes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories) {
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        val bindings = FragmentCategoriesBinding.bind(view)
        val adapter = CategoryAdapter(object: CategoryAdapter.CategoryClickListener{
            override fun onClick(category: String) {
                val action = CategoriesFragmentDirections.actionCategoriesFragmentToListContestFragment(ListContestTypes.CATEGORY, category)
                navController.navigate(action)
            }

        })


        bindings.apply {
            categoryRecyclerView.setHasFixedSize(true)
            categoryRecyclerView.adapter = adapter
        }

        adapter.submitList(categories)
    }

    companion object {
        val categories = listOf(
            Category("Short"),
            Category("Long"),
            Category("Ongoing"),
            Category("Upcoming")
        )
    }
}