package com.grevi.msx.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grevi.msx.network.response.MemberResponse
import com.grevi.msx.repos.Repository
import com.grevi.msx.utils.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemberViewModel(private  val repos: Repository) : ViewModel() {

    private val memberData : MutableLiveData<ResultResponse<MemberResponse>> = MutableLiveData()

    fun getMember() : LiveData<ResultResponse<MemberResponse>> {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repos.getMember()
            memberData.postValue(ResultResponse.loading(null, "Loading"))
            try {
                memberData.postValue(ResultResponse.success(data))
                Log.d("member_success", data.toString())
            } catch (e : Exception) {
                memberData.postValue(ResultResponse.error(null, e.message))
                Log.d("member_failure", e.message.toString())
            }
        }

        return memberData
    }

}