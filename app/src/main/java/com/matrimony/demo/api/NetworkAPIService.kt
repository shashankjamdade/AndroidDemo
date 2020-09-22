package com.matrimony.demo.api

import com.matrimony.demo.model.UserListResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkAPIService {

    @GET(ApiName.USER_LIST)
    suspend fun fetchUsers(@Query("results") results :Int): Response<UserListResponse>

}