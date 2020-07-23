package com.grevi.msx.repos

import com.grevi.msx.model.Member
import com.grevi.msx.network.ApiServices
import com.grevi.msx.network.SafeResponseCall

class Repository(private val apiServices: ApiServices) : SafeResponseCall() {

     suspend fun getMember() : List<Member>? {
        return safeCall(call = { apiServices.getMember() }, msg = "Error Fetching Data")
    }

}