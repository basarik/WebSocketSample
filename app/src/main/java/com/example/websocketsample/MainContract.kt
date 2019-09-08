package com.example.websocketsample

import com.example.websocketsample.base.BaseMvpPresenter
import com.example.websocketsample.base.BaseView

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
    }

    /**
     * list presenter
     */
    interface Presenter : BaseMvpPresenter<View> {
        fun getList()
    }
}