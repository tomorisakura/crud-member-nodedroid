package com.grevi.msx.network

import com.grevi.msx.utils.APIException
import com.grevi.msx.utils.Output
import com.grevi.msx.utils.ResultResponse
import retrofit2.Response

open class SafeResponseCall {

    suspend fun <T : Any> safeCall(call : suspend () -> Response<T>, msg :String) : T? {
        val result = safeCallOutput(call, msg)
        var output : T? = null
        when(result) {
            is Output.Success -> output = result.data
            is Output.Error -> throw APIException("Cant get result")
        }
        return output
    }

    private suspend fun <T : Any> safeCallOutput(call : suspend () -> Response<T>, err : String) : Output<T> {
        val response = call.invoke()
        return if (response.isSuccessful) {
            Output.Success(response.body()!!)
        } else {
            Output.Error(APIException("Something Wrong dude !"))
        }
    }

}