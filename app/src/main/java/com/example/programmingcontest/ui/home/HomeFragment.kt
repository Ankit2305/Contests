package com.example.programmingcontest.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.programmingcontest.R
import com.example.programmingcontest.databinding.FragmentHomeBinding
import com.example.programmingcontest.ui.home.adapter.ContestAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        val adapter = ContestAdapter()

        binding.apply {
            contestRecyclerView.setHasFixedSize(true)
            contestRecyclerView.adapter = adapter
        }

        viewModel.contestsIn24Hours.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}