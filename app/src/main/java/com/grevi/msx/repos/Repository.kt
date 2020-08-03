package com.grevi.msx.repos

import com.grevi.msx.model.Member
import com.grevi.msx.network.ApiServices
import com.grevi.msx.network.SafeResponseCall
import com.grevi.msx.network.response.MemberResponse

class Repository(private val apiServices: ApiServices) : SafeResponseCall() {

     suspend fun getMember() : MemberResponse? {
        return safeCall(call = { apiServices.getMember() })
    }

    suspend fun postMember(name : String, age : Int, address : String, hobby : String) : MemberResponse? {
        return safeCall(call = { apiServices.postMember(name, age, address, hobby) })
    }

}