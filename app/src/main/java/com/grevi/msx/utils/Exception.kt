package com.grevi.msx.utils

import java.io.IOException

open class NoConnectionException : IOException() {
    override val message: String?
        get() = "No Connection"
}


class APIException(msg: String?) : IOException(msg)