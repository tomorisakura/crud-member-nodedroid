package com.grevi.msx.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grevi.msx.repos.Repository
import com.grevi.msx.ui.MemberViewModel
import com.grevi.msx.ui.PostViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemberViewModel(repository) as T
    }
}

class PostViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}