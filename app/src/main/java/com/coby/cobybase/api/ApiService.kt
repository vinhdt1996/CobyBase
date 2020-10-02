package com.coby.cobybase.api

import com.coby.cobybase.constant.AppConst.ITEM_PER_PAGE
import com.coby.cobybase.model.Feed
import com.coby.cobybase.model.ListResponse
import com.coby.cobybase.model.ObjectResponse
import com.coby.cobybase.model.User
import com.coby.cobybase.param.LoginParam
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("users-login")
    suspend fun login(@Body login: LoginParam): Response<ObjectResponse<User>>

    @GET("search-user")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("current_per_page") perPage: Int = ITEM_PER_PAGE
    ): Response<ListResponse<User>>

    @GET("feed")
    suspend fun getFeeds(
        @Query("page") page: Int = 1,
        @Query("current_per_page") perPage: Int = ITEM_PER_PAGE
    ): Response<ListResponse<Feed>>
}