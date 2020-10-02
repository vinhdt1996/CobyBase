package com.coby.cobybase.repository

import com.coby.cobybase.constant.AppConst.FIRST_PAGE
import com.coby.cobybase.model.Feed
import com.coby.cobybase.vo.Listing

interface HomeRepository {
    suspend fun getFeeds(page: Int = FIRST_PAGE): Listing<Feed>
}