package com.example.programmingcontest.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.programmingcontest.R
import com.example.programmingcontest.core.model.Contest
import com.example.programmingcontest.databinding.FragmentHomeBinding
import com.example.programmingcontest.ui.adapters.ContestAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    val viewModel by viewModels<HomeViewModel>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        val binding = FragmentHomeBinding.bind(view)
        val adapter = ContestAdapter(
            object : ContestAdapter.ContestClickListener {
                override fun onClick(contest: Contest) {
                    val action = HomeFragmentDirections.actionHomeFragmentToContestFragment(contest)
                    navController.navigate(action)
                }
            }
        )

        binding.apply {
            contestRecyclerView.setHasFixedSize(true)
            contestRecyclerView.adapter = adapter
        }

        viewModel.contestsIn24Hours.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}