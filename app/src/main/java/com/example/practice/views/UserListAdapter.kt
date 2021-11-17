package com.example.practice.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.UserRowBinding
import com.example.practice.models.User
import com.example.practice.models.UserDataItem
import com.example.practice.view_models.UserDetailsViewModel

class UserListAdapter(context: Context, private var dataList: List<UserDataItem>,private val viewModel: UserDetailsViewModel) : RecyclerView.Adapter<UserListAdapter.ItemHolder>() {

    private lateinit var binding: UserRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        binding = UserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.userDetailsVM = viewModel
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(dataList[position])
        holder.position = position
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setDataList(list: List<UserDataItem>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    inner class ItemHolder(private val binding: UserRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserDataItem) {
            binding.item = item
        }

        fun setPosition(position: Int) {
            binding.itemPosition = position
        }
    }


}