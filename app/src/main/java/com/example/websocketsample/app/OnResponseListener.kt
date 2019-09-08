package com.example.websocketsample.app

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface OnResponseListener<in T> {
    /**
     * service response
     */
    fun onResponse(data: ApiResponse<T>)
}

data class ApiResponse<out T>(val success: T? = null, val error: Throwable? = null) {
    companion object {
        /**
         * response success
         */
        fun <T> success(data: T) = ApiResponse(success = data)

        /**
         * response fail
         */
        fun <T> error(err: Throwable) = ApiResponse<T>(error = err)
    }
}