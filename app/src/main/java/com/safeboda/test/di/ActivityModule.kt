package com.safeboda.test.di

import com.safeboda.test.MainActivity
import com.safeboda.test.users.di.UsersListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            UsersListFragmentProvider::class,
        ]
    )
    abstract fun mainActivity(): MainActivity

}