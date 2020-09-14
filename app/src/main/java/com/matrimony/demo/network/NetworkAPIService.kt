package com.matrimony.demo.network

import com.matrimony.demo.model.UserListResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkAPIService {

    @GET("api/")
    suspend fun fetchUsers(@Query("results") results :Int): Response<UserListResponse>

}