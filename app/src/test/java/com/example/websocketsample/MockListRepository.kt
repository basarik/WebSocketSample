package com.example.websocketsample

import com.example.websocketsample.app.ApiResponse
import com.example.websocketsample.app.OnResponseListener
import com.example.websocketsample.model.DataModel
import com.example.websocketsample.model.KeyValueModel


//
// Created by basari on 2019-09-09.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//

class MockListRepository : ListRepository {
    var isSuccess = true
    var isSocketOpen = true

    override fun getList(listener: OnResponseListener<DataModel>) {
        if (isSuccess) {
            listener.onResponse(ApiResponse.success(RESPONSE))
        } else {
            listener.onResponse(ApiResponse.error(Throwable(ERROR)))
        }
    }

    override fun openWebSocket(listener: MyWebSocketListener) {
        if (isSocketOpen) {
            listener.onConnectionOpen()
        } else {
            listener.onConnectionClose()
        }
    }

    override fun closeWebSocket() {
    }

    override fun sendMessageByWebSocket(message: String) {
    }

    companion object {
        const val ERROR = "Mock Error"
        val RESPONSE = DataModel(listOf(KeyValueModel(1, "Mock1")))
    }

}