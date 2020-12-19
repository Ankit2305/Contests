package com.example.programmingcontest.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class HomeViewModel @ViewModelInject constructor(private val repository: HomeRepository): ViewModel() {
    val contestsIn24Hours = repository.getContestsIn24Hours()
}