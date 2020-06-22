package com.example.whereto.ui.main.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whereto.databinding.ItemTripBinding
import com.example.whereto.model.Trip

class ProfileRecyclerAdapter : ListAdapter<Trip, ProfileRecyclerAdapter.ViewHolder>(ProfileDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder private constructor(
        private val binding: ItemTripBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Trip) {
            binding.tripItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ItemTripBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }


    class ProfileDiffCallback : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }


    }
}