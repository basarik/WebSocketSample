package com.example.websocketsample

import com.example.websocketsample.app.ApiResponse
import com.example.websocketsample.app.ListRepository
import com.example.websocketsample.app.OnResponseListener
import com.example.websocketsample.base.BasePresenter

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
class MainPresenter(private val repository: ListRepository) : BasePresenter<MainContract.View>(),
    MainContract.Presenter, OnResponseListener<DataModel> {
    override fun getList() {
        view?.showProgress()
        repository.getList(this)
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