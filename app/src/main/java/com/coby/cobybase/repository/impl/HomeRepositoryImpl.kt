package com.coby.cobybase.repository.impl

import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import com.coby.cobybase.api.ApiService
import com.coby.cobybase.data.remote.BaseDataSourceFactory
import com.coby.cobybase.model.Feed
import com.coby.cobybase.model.ListResponse
import com.coby.cobybase.model.User
import com.coby.cobybase.repository.HomeRepository
import com.coby.cobybase.vo.Listing
import com.coby.cobybase.vo.Resource
import retrofit2.Response

class HomeRepositoryImpl(val api: ApiService) : HomeRepository{

    override suspend fun getFeeds(page: Int): Listing<Feed> {
        val status = MutableLiveData<Resource<Feed>>()

        val sourceFactory = object : BaseDataSourceFactory<Feed, Feed>(status) {
            override suspend fun createXCall(page: Int): Response<ListResponse<Feed>> =
                api.getFeeds(page = page)

        }

        val pagedList = sourceFactory.toLiveData(20)

        return Listing(result = pagedList, status = status, refresh = {
            sourceFactory.sourceLiveData.value?.invalidate()
        })
    }

}