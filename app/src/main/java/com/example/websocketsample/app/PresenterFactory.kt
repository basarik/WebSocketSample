package com.example.websocketsample.app

import com.example.websocketsample.ListRepository
import com.example.websocketsample.ListRepositoryImpl
import com.example.websocketsample.MainContract
import com.example.websocketsample.MainPresenter

//
// Created by basari on 2019-09-06.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
open class PresenterFactory {
    /**
     * listRepository
     */
    open fun listRepository(): ListRepository =
        ListRepositoryImpl(App.instance.service)

    /**
     * listPresenter
     */
    fun listPresenter(): MainContract.Presenter = MainPresenter(listRepository())
}