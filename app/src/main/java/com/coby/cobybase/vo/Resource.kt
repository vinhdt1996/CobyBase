package com.coby.cobybase.vo

sealed class Resource<out T> {
    class Success<T>(val data: T? = null) : Resource<T>()
    class Error(val message: String, val code: Int) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object LoadingMore : Resource<Nothing>()
}