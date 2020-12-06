package com.razbakov.sandbox.network

import retrofit2.http.GET
import retrofit2.http.Url

interface HealthCheckService {
    @GET
    suspend fun checkHealth(@Url url: String)
}
