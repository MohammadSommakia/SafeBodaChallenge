package com.safeboda.test

import android.app.Application
import com.safeboda.data.di.PersistenceModule
import com.safeboda.test.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector

import javax.inject.Inject


/**
 * Base class for maintaining global application state.
 *
 */
class SafeBodaTestApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        initAppDependencyInjection()
    }

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder().persistenceModule(PersistenceModule(this))
            .build()
            .inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
