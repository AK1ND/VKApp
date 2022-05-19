package com.example.vkapp.retrofit

import com.example.vkapp.const.BASE_URL_OAUTH
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCreator {


    fun getUserAccessToken(): RetrofitService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_OAUTH)
            .build().create(RetrofitService::class.java)
        return retrofit
    }
}