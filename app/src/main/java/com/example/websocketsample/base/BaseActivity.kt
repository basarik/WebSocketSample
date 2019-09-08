package com.example.websocketsample.base

import android.app.ProgressDialog
import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.websocketsample.R

//
// Created by basari on 2019-09-06.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
abstract class BaseActivity : AppCompatActivity(), BaseView {

    /**
     * loadingDialog
     */
    private lateinit var loading: ProgressDialog

    /**
     * activity layout
     */
    abstract fun layoutResource(): Int

    /**
     * activity operations that will make on onCreate
     */
    abstract fun initActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource())
        loading = ProgressDialog(this)
        loading.setMessage(getString(R.string.loadingMessage))
        initActivity()
    }

    /**
     * show loading
     */
    override fun showProgress() {
        loading.show()
    }

    /**
     * hide loading
     */
    override fun hideProgress() {
        loading.hide()
    }

    override fun onError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}