package com.safeboda.test.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.safeboda.domain.models.user.model.User
import com.safeboda.test.users.databinding.UserListItemBinding
import javax.inject.Inject


class UserAdapter @Inject constructor() :
    PagingDataAdapter<User, UserAdapter.UserHolder>(UserComparator) {
    var listener: OnUserItemClickListener? = null





    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int) =
        UserHolder(
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, position) }
    }

    inner class UserHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(user: User, position: Int) {
            binding.user = user
            itemView.setOnClickListener {
                listener?.onItemClick(user, position)
            }
        }

    }

    object UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            oldItem == newItem
    }

    interface OnUserItemClickListener {
        fun onItemClick(item: User, position: Int)
    }
}

