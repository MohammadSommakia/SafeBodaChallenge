package com.safeboda.test.users.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.safeboda.core.base.BaseFragment
import com.safeboda.core.utils.ViewModelFactory
import com.safeboda.test.users.R
import com.safeboda.test.users.adapter.UserAdapter
import com.safeboda.test.users.databinding.FragmentUsersListBinding
import com.safeboda.test.users.databinding.UserListItemBinding
import com.safeboda.test.users.viewmodel.UsersListViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


class UsersListFragment :
    BaseFragment<FragmentUsersListBinding, UsersListViewModel>(R.layout.fragment_users_list) {
    private var adapter: UserAdapter? = null

    override fun onInitDataBinding() {
        viewBinding.viewmodel = viewModel
    }

    override fun initView() {
        adapter = UserAdapter()
        viewBinding.recyclerViewUsers.adapter = adapter
        collectUiState()

    }

    override fun getData() {
    }


    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pager.distinctUntilChanged().collectLatest { users ->
                adapter?.submitData(users)
            }
        }
    }
}

