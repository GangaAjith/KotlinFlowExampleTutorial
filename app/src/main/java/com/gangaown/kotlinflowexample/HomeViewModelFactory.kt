package com.gangaown.kotlinflowexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gangaown.kotlinflowexample.ui.HomeViewModel

class HomeViewModelFactory(private val dispatchers: DefaultDispatchers):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(dispatchers) as T
    }
}