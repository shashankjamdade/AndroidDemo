package com.demo.api

import com.demo.model.UserListResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkAPIService {

    @GET(ApiName.USER_LIST)
    suspend fun fetchUsers(@Query("results") results :Int): Response<UserListResponse>

}