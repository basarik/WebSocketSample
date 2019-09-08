package com.example.websocketsample

import com.example.websocketsample.app.ApiResponse
import com.example.websocketsample.app.ListRepository
import com.example.websocketsample.app.OnResponseListener
import com.example.websocketsample.base.BasePresenter
import com.example.websocketsample.model.ConnectionState
import com.example.websocketsample.model.DataModel

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
class MainPresenter(private val repository: ListRepository) : BasePresenter<MainContract.View>(),
    MainContract.Presenter, OnResponseListener<DataModel>, MyWebSocketListener {

    override fun getConnection(connectionState: ConnectionState) {
        when (connectionState) {
            ConnectionState.Connected -> {
                //close connection
                closeConnection()
            }
            ConnectionState.Disconnected -> {
                openConnection()
            }
        }
    }

    override fun onConnectionOpen() {
        view?.hideProgress()
        view?.onConnected()
    }

    override fun onConnectionClose() {
        view?.hideProgress()
        view?.onDisConnected()
    }

    override fun onMessage(message: String) {
        view?.hideProgress()
        view?.onMessage(message)
    }

    override fun onFailure() {
        view?.hideProgress()
        view?.onFailure()
    }

    override fun getList() {
        view?.showProgress()
        repository.getList(this)
    }

    override fun openConnection() {
        view?.showProgress()
        repository.openWebSocket(this)
    }

    override fun closeConnection() {
        view?.showProgress()
        repository.closeWebSocket()
    }

    override fun sendMessage(connectionState: ConnectionState, message: String) {
        view?.showProgress()
        repository.sendMessageByWebSocket(message)
    }

    override fun onResponse(keyValue: ApiResponse<DataModel>) {
        view?.hideProgress()
        keyValue.error?.apply {
            view?.onError(this)
        }

        keyValue.success?.apply {
            view?.onGetList(this.data)
        }
    }
}