package com.safeboda.test.users.di

import androidx.lifecycle.ViewModel
import com.safeboda.core.utils.ViewModelKey
import com.safeboda.test.users.viewmodel.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UsersListModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    fun bindUsersListViewModel(viewModel: UsersListViewModel): ViewModel

}