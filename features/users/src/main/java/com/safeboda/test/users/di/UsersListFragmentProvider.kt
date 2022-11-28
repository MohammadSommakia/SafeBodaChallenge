package com.safeboda.test.users.di

import com.safeboda.domain.scopes.FeatureScope
import com.safeboda.test.users.ui.UsersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UsersListFragmentProvider {

    @FeatureScope
    @ContributesAndroidInjector(
        modules = [
            UsersListModule::class
        ]
    )
    fun contributeUsersListFragment(): UsersListFragment

}