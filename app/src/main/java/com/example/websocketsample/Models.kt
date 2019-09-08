package com.example.websocketsample

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//
// Created by basari on 2019-09-06.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
data class KeyValueModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Serializable

data class DataModel(
    @SerializedName("data")
    val data: List<KeyValueModel>
) : Serializable