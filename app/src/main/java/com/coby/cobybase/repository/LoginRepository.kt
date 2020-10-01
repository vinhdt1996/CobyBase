package com.coby.cobybase.repository

import androidx.lifecycle.LiveData
import com.coby.cobybase.model.User
import com.coby.cobybase.param.LoginParam
import com.coby.cobybase.vo.Resource

interface LoginRepository {

    suspend fun login(param: LoginParam): LiveData<Resource<User>>

}