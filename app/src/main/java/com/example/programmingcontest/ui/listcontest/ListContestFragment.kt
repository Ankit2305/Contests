package com.example.programmingcontest.ui.listcontest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.programmingcontest.R
import com.example.programmingcontest.core.model.Contest
import com.example.programmingcontest.databinding.FragmentListcontestBinding
import com.example.programmingcontest.ui.adapters.ContestAdapter
import com.example.programmingcontest.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListContestFragment: Fragment(R.layout.fragment_listcontest) {
    val args: ListContestFragmentArgs by navArgs()
    val viewModel by viewModels<ListContestViewModel>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        viewModel.getContest(args.type, args.value)

        val adapter = ContestAdapter(
            object : ContestAdapter.ContestClickListener {
                override fun onClick(contest: Contest) {
                    val action = ListContestFragmentDirections.actionListContestFragmentToContestFragment(contest)
                    navController.navigate(action)
                }
            }
        )

        val binding = FragmentListcontestBinding.bind(view)
        binding.apply {
            contestRecyclerView.setHasFixedSize(true)
            contestRecyclerView.adapter = adapter
        }

        viewModel.contests.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty())
                binding.noContestMessage.visibility = View.VISIBLE
            else
                binding.noContestMessage.visibility = View.GONE
            adapter.submitList(it)
        })
    }
}