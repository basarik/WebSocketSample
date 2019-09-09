package com.example.websocketsample

import com.example.websocketsample.model.ConnectionState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.internal.verification.AtLeast

class MainPresenterTest {


//
// Created by basari on 2019-09-09.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//

    /**
     * mock view
     */
    private val view = mock<MainContract.View>()

    /**
     * mock repository
     */
    private val repository = MockListRepository()

    /**
     * category presenter
     */
    private var presenter = MainPresenter(repository)

    @Before
    fun setup() {
        presenter.attach(view)
    }

    /**
     * category success scenario
     */
    @Test
    fun getListFail() {
        repository.isSuccess = false
        presenter.getList()

        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).onError(any())
    }

    @Test
    fun getListSuccess() {
        repository.isSuccess = true
        presenter.getList()

        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).onGetList(any())
    }

    @Test
    fun openConnection() {
        presenter.openConnection()

        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).onConnected()
    }

    @Test
    fun closeConnection() {
        presenter.openConnection()
        presenter.closeConnection()

        verify(view, AtLeast(1)).showProgress()
        verify(view).onDisConnected()
    }

    @Test
    fun sendMessage() {
        presenter.openConnection()
        presenter.sendMessage(ConnectionState.Connected, "mock message")

        verify(view, AtLeast(1)).showProgress()
        verify(view).onMessage(any())
    }
}
