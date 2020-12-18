package com.example.programmingcontest.ui.site

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.programmingcontest.core.model.Contest

class SiteViewModel @ViewModelInject constructor(private val repository: SiteRepository): ViewModel() {
    val sites = repository.getSites()

}