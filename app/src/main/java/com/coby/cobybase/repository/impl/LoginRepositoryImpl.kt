package com.coby.cobybase.repository.impl

import androidx.lifecycle.LiveData
import com.coby.cobybase.api.ApiService
import com.coby.cobybase.data.remote.NetworkBoundResource
import com.coby.cobybase.model.ObjectResponse
import com.coby.cobybase.model.User
import com.coby.cobybase.param.LoginParam
import com.coby.cobybase.repository.LoginRepository
import com.coby.cobybase.vo.Resource
import retrofit2.Response

class LoginRepositoryImpl(val api: ApiService) : LoginRepository {

    override suspend fun login(param: LoginParam): LiveData<Resource<User>> {
        return object : NetworkBoundResource<ObjectResponse<User>, User>() {
            override suspend fun createCall(): Response<ObjectResponse<User>> = api.login(param)

            override fun processResponse(response: ObjectResponse<User>): User? = response.data
        }.build().asLiveData()
    }
}