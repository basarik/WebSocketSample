package com.example.websocketsample.app

//
// Created by basari on 2019-09-09.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
//region String
fun String.isNumeric(): Boolean = this.matches("\\d+".toRegex())