package com.example.programmingcontest.ui.explore

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.programmingcontest.R
import com.example.programmingcontest.databinding.FragmentExploreBinding
import com.example.programmingcontest.ui.explore.adapter.SiteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {
    val viewModel by viewModels<ExploreViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentExploreBinding.bind(view)
        val adapter = SiteAdapter()

        binding.apply {
            siteRecylerView.setHasFixedSize(true)
            siteRecylerView.adapter = adapter
        }

        viewModel.sites.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }
}