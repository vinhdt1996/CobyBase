package com.coby.cobybase.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.coby.cobybase.model.ListResponse
import com.coby.cobybase.vo.Resource
import retrofit2.Response

abstract class BaseDataSourceFactory<I, O>(val status: MutableLiveData<Resource<O>>) :
    DataSource.Factory<Int, O>() {
    val sourceLiveData = MutableLiveData<BasePageKeyedDataSource<I, O>>()
    override fun create(): DataSource<Int, O> {
        val source = object : BasePageKeyedDataSource<I, O>(status = status) {
            override suspend fun createCall(page: Int): Response<ListResponse<I>> =
                createXCall(page)

            override suspend fun handleResponse(
                items: ListResponse<I>,
                isFirstLoad: Boolean
            ): List<O> = handleXResponse(items, isFirstLoad)
        }
        sourceLiveData.postValue(source)

        return source
    }

    @MainThread
    protected abstract suspend fun createXCall(page: Int): Response<ListResponse<I>>

    @WorkerThread
    protected open suspend fun handleXResponse(
        items: ListResponse<I>,
        isFirstLoad: Boolean
    ): List<O> {
        val result = arrayListOf<O>()
        result.addAll(items.data as List<O>)
        return result
    }

}