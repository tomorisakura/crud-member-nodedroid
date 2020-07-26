package com.grevi.msx.network.response

import com.google.gson.annotations.SerializedName
import com.grevi.msx.model.Member

data class MemberResponse(
    @SerializedName("result") val result : MutableList<Member>
)