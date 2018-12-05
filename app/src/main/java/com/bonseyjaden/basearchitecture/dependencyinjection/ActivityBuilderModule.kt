package com.bonseyjaden.basearchitecture.dependencyinjection

import com.bonseyjaden.basearchitecture.dependencyinjection.scope.ActivityScope
import com.bonseyjaden.basearchitecture.feature.main.MainActivity
import com.bonseyjaden.basearchitecture.feature.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The [Module] class declares how to inject an Activity instance into corresponding
 * {@link Module}
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MainModule::class])
    @ActivityScope
    abstract fun bindMainActivity(): MainActivity
}