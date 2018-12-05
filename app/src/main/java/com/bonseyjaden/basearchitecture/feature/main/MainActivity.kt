package com.bonseyjaden.basearchitecture.feature.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.bonseyjaden.basearchitecture.R
import com.bonseyjaden.basearchitecture.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleEvent()
        observe()
    }

    private fun handleEvent() {
        btn_get_key.setOnClickListener { viewModel.getData() }
    }

    private fun observe() {
        viewModel.key.observe(this, Observer {
            tv_key.text = it
        })
    }
}
