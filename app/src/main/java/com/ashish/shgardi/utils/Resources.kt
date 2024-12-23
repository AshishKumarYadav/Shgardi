package com.ashish.shgardi.utils

sealed class Resources<T>(
    val data: T? = null,
    val message:String?= null,
    val code :Int? = null
) {

    class Success<T>(data:T) : Resources<T>(data)
    class Error<T>(message: String?,code: Int?) : Resources<T>(message =message,code = code)
    class Loading<T> : Resources<T>()
}