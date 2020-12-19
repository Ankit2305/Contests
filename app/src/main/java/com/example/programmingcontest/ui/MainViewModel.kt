package com.example.programmingcontest.ui;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel

class MainViewModel @ViewModelInject constructor(private val repository: MainRepository):
    ViewModel() {
    fun updateDB() {
        repository.updateDB()
    }
}
