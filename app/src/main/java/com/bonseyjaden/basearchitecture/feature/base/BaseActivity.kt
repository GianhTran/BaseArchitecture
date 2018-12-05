package com.bonseyjaden.basearchitecture.feature.base

import com.bonseyjaden.basearchitecture.helper.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator
}