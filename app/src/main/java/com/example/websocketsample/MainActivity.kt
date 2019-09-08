package com.example.websocketsample

import com.example.websocketsample.app.App
import com.example.websocketsample.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun layoutResource(): Int = R.layout.activity_main

    override fun initActivity() {
        presenter = App.instance.presenterFactory.listPresenter()
        presenter.attach(this)
        presenter.getList()
    }

    override fun onGetList(list: List<KeyValueModel>) {
        activity_main_rv_list.adapter = ListAdapter(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
