package com.coby.cobybase.vo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    // the LiveData of paged lists for the UI to observe
    val result: LiveData<PagedList<T>>,
    val status: LiveData<Resource<T>>,
    // represents the network request status to show to the user
    val refresh: () -> Unit
    // retries any failed requests.
)
