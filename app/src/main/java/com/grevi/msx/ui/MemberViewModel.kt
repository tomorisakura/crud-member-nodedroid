package com.grevi.msx.ui

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grevi.msx.model.Member
import com.grevi.msx.repos.Repository
import com.grevi.msx.utils.NoInternetException
import com.grevi.msx.utils.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import java.io.IOException
import java.lang.Exception

class MemberViewModel(private  val repos: Repository) : ViewModel() {

    private val memberData : MutableLiveData<ResultResponse<List<Member>>> = MutableLiveData()

    fun getMember() : LiveData<ResultResponse<List<Member>>> {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repos.getMember()
            memberData.postValue(ResultResponse.loading(null, "Loading"))
            try {
                memberData.postValue(ResultResponse.success(data))
                Log.d("member_success", data.toString())
            } catch (e : Exception) {
                memberData.postValue(ResultResponse.error(null, e.message))
                Log.d("member_failure", e.message.toString())
            }catch (e : NoInternetException) {
                memberData.postValue(ResultResponse.unavailable(null, e.message))
            }
        }

        return memberData
    }

}