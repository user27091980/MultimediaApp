package com.example.multimediaapp.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.multimediaapp.network.MultimediaApiService

class MainVMFactory(private val api: MultimediaApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainVM(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


