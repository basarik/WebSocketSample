package com.example.websocketsample

import com.example.websocketsample.app.ApiResponse
import com.example.websocketsample.app.ListRepository
import com.example.websocketsample.app.OnResponseListener
import com.example.websocketsample.app.isNumeric
import com.example.websocketsample.base.BasePresenter
import com.example.websocketsample.model.ConnectionState
import com.example.websocketsample.model.DataModel
import com.example.websocketsample.model.KeyValueModel

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
class MainPresenter(private val repository: ListRepository) : BasePresenter<MainContract.View>(),
    MainContract.Presenter, OnResponseListener<DataModel>, MyWebSocketListener {

    private lateinit var list: List<KeyValueModel>

    override fun changeListItem(index: Int, name: String) {
        view?.onListItemChange(index, name)
    }

    override fun checkMessage(message: String) {
        message.apply {
            val array = this.split("-")

            if (array.size == 2) {

                val id = if (array[0].isNumeric()) array[0].toInt() else -1

                val index = list.indexOfFirst { it.id == id }

                changeListItem(index, array[1])

            } else {
                //do nothing
            }
        }
    }

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
            list = this.data
            view?.onGetList(list)
        }
    }
}