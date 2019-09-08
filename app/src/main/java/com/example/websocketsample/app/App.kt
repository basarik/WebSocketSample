package com.example.websocketsample.app

import android.app.Application
import com.example.websocketsample.api.ServiceCall
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
// Created by basari on 2019-09-06.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
class App : Application() {

    /**
     * base Url
     */
    private val baseUrl = "https://my-json-server.typicode.com/emredirican/"

    /**
     * service
     */
    lateinit var service: ServiceCall

    /**
     * presenter factory
     */
    lateinit var presenterFactory: PresenterFactory

    override fun onCreate() {
        super.onCreate()

        instance = this

        presenterFactory = PresenterFactory()

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ServiceCall::class.java)
    }

    companion object {
        /**
         * instance of application
         */
        lateinit var instance: App
    }
}