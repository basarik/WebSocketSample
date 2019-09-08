package com.example.websocketsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.websocketsample.model.KeyValueModel
import kotlinx.android.synthetic.main.list_item_model.view.*

//
// Created by basari on 2019-09-08.
// Copyright (c) 2019 kubuzcu. All rights reserved.
//
class ListAdapter(private val list: List<KeyValueModel>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_model, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.id.text = item.id.toString()
        holder.name.text = item.name
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.list_item_model_id
        val name: TextView = view.list_item_model_name
    }
}




