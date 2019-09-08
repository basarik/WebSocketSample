package com.example.websocketsample.base

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface BaseMvpPresenter<in T : BaseView> {
    /**
     * presenter attach here
     */
    fun attach(baseView: T)

    /**
     * presenter detach here
     */
    fun detach()
}