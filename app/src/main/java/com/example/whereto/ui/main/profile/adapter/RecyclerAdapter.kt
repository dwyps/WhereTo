package com.example.whereto.ui.main.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whereto.databinding.ItemTripBinding
import com.example.whereto.data.model.Trip

class RecyclerAdapter constructor (
    private val listener: OnItemListener? = null
) : ListAdapter<Trip, RecyclerAdapter.ViewHolder>(ProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder private constructor(
        private val binding: ItemTripBinding,
        private val listener: OnItemListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Trip) {

            binding.tripItem = item

            binding.itemTripConstraintLayout.setOnClickListener{
                listener?.onItemClick(adapterPosition, item)
            }

            binding.itemTripConstraintLayout.setOnLongClickListener {
                listener?.onItemLongClick(adapterPosition, item)
                true
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onItemListener: OnItemListener?
            ): ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ItemTripBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, onItemListener)
            }
        }
    }

    interface OnItemListener {

        fun onItemClick(position: Int, trip: Trip)
        fun onItemLongClick(position: Int, trip: Trip)
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