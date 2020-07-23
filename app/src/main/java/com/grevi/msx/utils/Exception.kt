package com.grevi.msx.utils

import java.io.IOException

class NoInternetException(msg : String?) : IOException(msg)
class APIException(msg: String?) : IOException(msg)