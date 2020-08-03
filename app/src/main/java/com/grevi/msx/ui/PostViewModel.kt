package com.grevi.msx.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grevi.msx.repos.Repository
import com.grevi.msx.utils.Auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(private val repository: Repository) : ViewModel() {

    var name : MutableLiveData<String> = MutableLiveData()
    var age : MutableLiveData<String> = MutableLiveData()
    var address : MutableLiveData<String> = MutableLiveData()
    var hobby : MutableLiveData<String> = MutableLiveData()

    var listener : Auth? = null

    fun postButton() {
        if (name.value == null || age.value == null) {
            listener?.toastTest("Item not null")
        } else if (address.value == null || hobby.value == null) {
            listener?.toastTest("Item not null")
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                repository.postMember(name = name.value.toString(),
                age = age.value!!.toInt(),
                address = address.value.toString(),
                hobby = hobby.value.toString())
            }
            listener?.toastTest("Post Successfully !")
            listener?.success()
        }
    }

}