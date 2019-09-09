package com.example.websocketsample

import com.example.websocketsample.app.App
import com.example.websocketsample.base.BaseActivity
import com.example.websocketsample.model.ConnectionState
import com.example.websocketsample.model.KeyValueModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var adapter: ListAdapter
    private var connectionState: ConnectionState = ConnectionState.Disconnected
    private var listOfItems = listOf<KeyValueModel>()

    override fun layoutResource(): Int = R.layout.activity_main

    override fun initActivity() {
        presenter = App.instance.presenterFactory.listPresenter()
        presenter.attach(this)
        presenter.getList()

        activity_main_button_send.setOnClickListener {
            activity_main_et_message.text.let {
                presenter.sendMessage(connectionState, it.toString())
            }
        }

        activity_main_button_connect.setOnClickListener {
            presenter.getConnection(connectionState)
        }
    }

    override fun onListItemChange(index: Int, name: String) {
        changeItem(index, name)
    }

    override fun onConnected() {
        updateTitle(getString(R.string.connected))
        updateConnectionButton(R.string.disconnect)
        connectionState = ConnectionState.Connected
        enableButton(true)
    }

    override fun onDisConnected() {
        updateTitle(getString(R.string.disconnected))
        updateConnectionButton(R.string.connect)
        connectionState = ConnectionState.Disconnected
        enableButton(false)
    }

    override fun onMessage(message: String) {
        updateTitle(getString(R.string.youWrote, message))
        presenter.checkMessage(message)
    }

    override fun onFailure() {
        updateTitle(getString(R.string.errorWebSocket))
        enableButton(false)
    }

    override fun onGetList(list: List<KeyValueModel>) {
        listOfItems = list
        adapter = ListAdapter(listOfItems)
        activity_main_rv_list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    private fun updateTitle(actionTitle: String) {
        runOnUiThread {
            title = actionTitle
        }
    }

    private fun updateConnectionButton(buttonName: Int) {
        runOnUiThread {
            activity_main_button_connect.text = getString(buttonName)
        }
    }

    private fun enableButton(enabled: Boolean) {
        runOnUiThread {
            activity_main_button_send.isEnabled = enabled
        }
    }

    private fun changeItem(index: Int, name: String) {
        runOnUiThread {
            listOfItems[index].name = name
            adapter.notifyItemChanged(index)
        }
    }
}
