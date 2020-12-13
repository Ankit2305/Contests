package com.example.programmingcontest.ui.listcontest

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.programmingcontest.core.model.Contest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListContestViewModel @ViewModelInject constructor(val repository: ListContestRepository): ViewModel() {
    val contests = MutableLiveData<List<Contest>>()

    fun getContest(type: Int, value: String) {
        GlobalScope.launch {
            val updatedContests = repository.getContest(type, value)
            withContext(Dispatchers.Main) {
                contests.value = updatedContests
            }
        }
    }
}