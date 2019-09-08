package com.example.websocketsample.base

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
interface BaseView {
    /**
     * show progress dialog when service call starts
     */
    fun showProgress()

    /**
     * * hide progress dialog when service call ended
     */
    fun hideProgress()

    /**
     * show error message when service call ended
     */
    fun onError(t: Throwable)
}