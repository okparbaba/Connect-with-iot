package com.greenhackers.iot2

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("api/client/v2.0/app/test-jfdau/service/api/incoming_webhook/webhook0")
    fun getData():Call<List<Response>>
}