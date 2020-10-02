package com.coby.cobybase.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.coby.cobybase.model.Feed
import com.coby.cobybase.repository.HomeRepository
import com.coby.cobybase.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

    val feedsLiveData = MediatorLiveData<PagedList<Feed>>()
    val loadStateLiveData = MediatorLiveData<Resource<Feed>>()

    fun getFeeds() {
        viewModelScope.launch {
            val request = repository.getFeeds()

            feedsLiveData.addSource(request.result) {
                feedsLiveData.value = it
            }

            feedsLiveData.addSource(request.status) {
                loadStateLiveData.value = it
            }
        }
    }
}