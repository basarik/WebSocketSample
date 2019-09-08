package com.example.websocketsample.base

//
// Created by basari on 2019-09-06.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
open class BasePresenter<T : BaseView> : BaseMvpPresenter<T> {
    /**
     * related presenter view
     */
    var view: T? = null

    override fun attach(baseView: T) {
        view = baseView
    }

    override fun detach() {
        view = null
    }
}