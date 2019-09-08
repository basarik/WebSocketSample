package com.example.websocketsample

//
// Created by basari on 2019-09-09.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface MyWebSocketListener {
    fun onConnectionOpen()
    fun onConnectionClose()
    fun onMessage(message: String)
    fun onFailure()
}