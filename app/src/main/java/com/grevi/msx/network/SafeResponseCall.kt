package com.grevi.msx.network

import com.grevi.msx.utils.APIException
import kotlinx.serialization.json.JsonException
import retrofit2.Response
import java.lang.StringBuilder

open class SafeResponseCall {

    suspend fun <T : Any> safeCall(call : suspend () -> Response<T>) : T? {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()
        } else {
            val error = response.errorBody()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(it)
                }catch (e : JsonException) {
                    message.append(e.message)
                }
            }
            message.append("\n code : ${response.code()}")
            throw APIException(message.toString())
        }
    }
}