package com.example.programmingcontest.ui.site

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.programmingcontest.R
import com.example.programmingcontest.databinding.FragmentSiteBinding
import com.example.programmingcontest.ui.adapters.SiteAdapter
import com.example.programmingcontest.ui.listcontest.ListContestTypes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SiteFragment : Fragment(R.layout.fragment_site) {
    val viewModel by viewModels<SiteViewModel>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        val binding = FragmentSiteBinding.bind(view)
        val adapter = SiteAdapter(object: SiteAdapter.SiteClickListener {
            override fun onClick(site: String) {
                val action = SiteFragmentDirections.actionExploreFragmentToListContestFragment(ListContestTypes.SITE, site)
                navController.navigate(action)
            }

        })

        binding.apply {
            siteRecyclerView.setHasFixedSize(true)
            siteRecyclerView.adapter = adapter
        }

        viewModel.sites.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }
}