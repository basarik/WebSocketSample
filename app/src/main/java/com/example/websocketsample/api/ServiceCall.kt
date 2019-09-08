package com.example.websocketsample.api


import com.example.websocketsample.DataModel
import retrofit2.Call
import retrofit2.http.GET

//
// Created by basari on 2019-09-06.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface ServiceCall {

    @GET("mock-api/db")
    fun getList(): Call<DataModel>
}