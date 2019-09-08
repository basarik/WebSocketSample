package com.example.websocketsample

import com.example.websocketsample.base.BaseMvpPresenter
import com.example.websocketsample.base.BaseView
import com.example.websocketsample.model.ConnectionState
import com.example.websocketsample.model.KeyValueModel

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface MainContract {
    /**
     * list view
     */
    interface View : BaseView {
        fun onGetList(list: List<KeyValueModel>)
        fun onConnected()
        fun onDisConnected()
        fun onMessage(message: String)
        fun onFailure()
    }

    /**
     * list presenter
     */
    interface Presenter : BaseMvpPresenter<View> {
        fun getList()
        fun openConnection()
        fun closeConnection()
        fun getConnection(connectionState: ConnectionState)
        fun sendMessage(connectionState: ConnectionState, message:String)
    }
}