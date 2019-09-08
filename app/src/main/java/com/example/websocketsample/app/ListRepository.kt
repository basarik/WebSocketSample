package com.example.websocketsample.app

import com.example.websocketsample.MyWebSocketListener
import com.example.websocketsample.api.ServiceCall
import com.example.websocketsample.model.DataModel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface ListRepository {
    fun getList(listener: OnResponseListener<DataModel>)
    fun openWebSocket(listener: MyWebSocketListener)
    fun closeWebSocket()
    fun sendMessageByWebSocket(message: String)
}

class ListRepositoryImpl(private val service: ServiceCall) : ListRepository {

    private lateinit var ws: WebSocket

    private val client: OkHttpClient = OkHttpClient()

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

    override fun openWebSocket(listener: MyWebSocketListener) {
        val request: Request = Request.Builder().url(Constants.WEB_SOCKET_URL).build()
        ws = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
                super.onOpen(webSocket, response)
                listener.onConnectionOpen()
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                listener.onConnectionClose()
            }

            override fun onFailure(
                webSocket: WebSocket,
                t: Throwable,
                response: okhttp3.Response?
            ) {
                super.onFailure(webSocket, t, response)
                listener.onFailure()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                listener.onMessage(text)
            }
        })
    }

    override fun closeWebSocket() {
        ws.close(1000, "Disconnected by user")
    }

    override fun sendMessageByWebSocket(message: String) {
        ws.send(message)
    }
}