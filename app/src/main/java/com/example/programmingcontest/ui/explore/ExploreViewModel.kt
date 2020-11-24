package com.example.programmingcontest.ui.explore

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.programmingcontest.core.model.Contest

class ExploreViewModel @ViewModelInject constructor(private val repository: ExploreRepository): ViewModel() {
    val contests = repository.getContests()
    val sites = repository.getSites()

}