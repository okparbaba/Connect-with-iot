package com.greenhackers.iot2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        inline fun <reified T> retrofitCli(): T {
            val ret = Retrofit.Builder()
                .baseUrl("http://webhooks.mongodb-stitch.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(T::class.java)
            return ret
        }
    }
}