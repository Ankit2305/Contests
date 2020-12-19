package com.example.programmingcontest.ui;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.programmingcontest.core.model.ContestsAppVersion

class MainViewModel @ViewModelInject constructor(private val repository: MainRepository):
    ViewModel() {
    val isLatestVersionAvailable = MutableLiveData<Boolean>()

    fun updateDB() {
        repository.updateDB()
    }

    fun checkVersion() {
        val latestVersion = repository.getLatestVersion()
        val currentVersion = ContestsAppVersion.currentVersion()
        isLatestVersionAvailable.value = (latestVersion != currentVersion)
    }
}
