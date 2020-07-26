package com.grevi.msx.utils

import android.content.Context
import android.widget.Toast

data class ResultResponse<out T>(val status: Status, val data : T? , val msg : String?) {

    companion object {
        fun <T> success(data : T?) : ResultResponse<T> {
            return ResultResponse(Status.SUCCESS, data, null)
        }
        fun <T> loading(data: T? = null, msg: String?) : ResultResponse<T> {
            return ResultResponse(Status.LOADING, data, msg)
        }
        fun <T> error(data: T?, msg: String?) : ResultResponse<T> {
          return ResultResponse(Status.ERROR, data, msg)
        }
    }

}

enum class Status{
    SUCCESS,
    LOADING,
    ERROR
}

//public component
fun toast(context : Context, msg: String?) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}