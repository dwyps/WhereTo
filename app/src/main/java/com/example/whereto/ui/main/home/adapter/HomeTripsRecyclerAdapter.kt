package com.example.whereto.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whereto.databinding.ItemHomeTripBinding
import com.example.whereto.databinding.ItemTripBinding
import com.example.whereto.data.model.Trip

class HomeTripsRecyclerAdapter (
    private val listener: OnItemListener? = null
    ): ListAdapter<Trip, HomeTripsRecyclerAdapter.ViewHolder>(ProfileDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder private constructor(
        private val binding: ItemHomeTripBinding,
        private val listener: OnItemListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Trip) {
            binding.tripItem = item

            binding.itemHomeTripCardView.setOnClickListener {
                listener?.onItemClick(adapterPosition, item)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, listener: OnItemListener?): ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ItemHomeTripBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, listener)
            }
        }
    }

    interface OnItemListener {

        fun onItemClick(position: Int, trip: Trip)
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