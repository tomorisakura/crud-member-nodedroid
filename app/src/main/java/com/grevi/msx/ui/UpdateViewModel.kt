package com.grevi.msx.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grevi.msx.repos.Repository

class UpdateViewModel(private val repository: Repository) : ViewModel() {

    val name : MutableLiveData<String> = MutableLiveData()
    val age : MutableLiveData<String> = MutableLiveData()
    val address : MutableLiveData<String> = MutableLiveData()
    val hobby : MutableLiveData<String> = MutableLiveData()

}