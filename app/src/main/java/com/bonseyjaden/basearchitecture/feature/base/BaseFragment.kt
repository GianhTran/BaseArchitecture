package com.bonseyjaden.basearchitecture.feature.base

import android.os.Bundle
import android.view.View
import com.bonseyjaden.basearchitecture.helper.navigation.Navigator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleEvent()
        observe()
    }

    open fun initView() {}

    open fun handleEvent() {}

    open fun observe() {}
}