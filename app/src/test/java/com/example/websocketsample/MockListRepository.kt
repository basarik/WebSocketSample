package com.example.websocketsample

import com.example.websocketsample.app.ApiResponse
import com.example.websocketsample.app.OnResponseListener
import com.example.websocketsample.model.DataModel
import com.example.websocketsample.model.KeyValueModel


//
// Created by basari on 2019-09-09.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//

open class MockListRepository : ListRepository {
    var isSuccess = true
    var localListener:MyWebSocketListener? = null

    override fun getList(listener: OnResponseListener<DataModel>) {
        if (isSuccess) {
            listener.onResponse(ApiResponse.success(RESPONSE))
        } else {
            listener.onResponse(ApiResponse.error(Throwable(ERROR)))
        }
    }

    override fun openWebSocket(listener: MyWebSocketListener) {
        localListener = listener
        listener.onConnectionOpen()
    }

    override fun closeWebSocket() {
        localListener?.onConnectionClose()
    }

    override fun sendMessageByWebSocket(message: String) {
        localListener?.onMessage(message)
    }

    companion object {
        const val ERROR = "Mock Error"
        val RESPONSE = DataModel(listOf(KeyValueModel(1, "Mock1")))
    }

}