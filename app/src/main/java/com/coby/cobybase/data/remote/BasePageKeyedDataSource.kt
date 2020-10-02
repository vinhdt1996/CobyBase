package com.coby.cobybase.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.coby.cobybase.MainApplication
import com.coby.cobybase.model.ListResponse
import com.coby.cobybase.model.ObjectResponse
import com.coby.cobybase.vo.Resource
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

abstract class BasePageKeyedDataSource<I, O>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    val status: MutableLiveData<Resource<O>>
) : PageKeyedDataSource<Int, O>(), CoroutineScope {

    private val job = Job()
    val TAG = BasePageKeyedDataSource::class.java.name

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, O>
    ) {
        if (MainApplication.instance.isNetworkConnected()) {
            launch {
                fetchFromNetwork(initialCallback = callback)
            }
        } else {
            // todo
//            AppEvent.onNoInternet()
            setValue(Resource.Error("", 404))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, O>) {
        if (MainApplication.instance.isNetworkConnected()) {
            launch {
                fetchFromNetwork(params.key, callback = callback)
            }
        } else {
            // todo
//            AppEvent.onNoInternet()
            setValue(Resource.Error("", 404))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, O>) {

    }

    private suspend fun fetchFromNetwork(
        page: Int = 1,
        initialCallback: LoadInitialCallback<Int, O>? = null,
        callback: LoadCallback<Int, O>? = null
    ) {
        if (page == 1) setValue(Resource.Loading) else setValue(Resource.LoadingMore)

        val apiResponse = createCall(page)

        if (apiResponse.isSuccessful) {
            val body = apiResponse.body()
            when (apiResponse.code()) {
                200, 201 -> {
                    body?.let {
                        initialCallback?.onResult(handleResponse(it, true), null, 2)
                        callback?.onResult(handleResponse(it, false), page + 1)
                        setValue(Resource.Success())
                    }
                }
                else -> {
                    // handle show network error when call init
                    setValue(Resource.Error(apiResponse.message(), apiResponse.code()))
                }
            }
        } else {
            val response =
                Gson().fromJson(apiResponse.errorBody()?.string(), ObjectResponse::class.java)
            val errorMsg = response?.detail ?: ""
            // handle show network error when call init
            setValue(Resource.Error(errorMsg, apiResponse.code()))
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<O>) {
        if (status.value != newValue) {
            status.postValue(newValue)
        }
    }

    @MainThread
    protected abstract suspend fun createCall(page: Int): Response<ListResponse<I>>

    @WorkerThread
    protected abstract suspend fun handleResponse(
        items: ListResponse<I>,
        isFirstLoad: Boolean = false
    ): List<O>

}