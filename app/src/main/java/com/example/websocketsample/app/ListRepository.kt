package com.example.websocketsample.app

import com.example.websocketsample.DataModel
import com.example.websocketsample.api.ServiceCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface ListRepository {
    fun getList(listener: OnResponseListener<DataModel>)
}

class ListRepositoryImpl(private val service: ServiceCall) : ListRepository {
    override fun getList(listener: OnResponseListener<DataModel>) {
        service.getList().enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                listener.onResponse(ApiResponse.error(t))
            }

            override fun onResponse(
                call: Call<DataModel>,
                response: Response<DataModel>
            ) {
                response.body().apply {
                    if (response.code() == Constants.RESPONSE_SUCCESS_CODE) {
                        listener.onResponse(ApiResponse.success(response.body()!!))
                    } else {
                        listener.onResponse(ApiResponse.error(Throwable(Constants.ERROR_OCCURRED)))
                    }
                }
            }
        })
    }
}