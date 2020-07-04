package com.example.whereto.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whereto.databinding.ItemDealBinding
import com.example.whereto.data.model.Deal


class HomeRecyclerAdapter (
    private val listener: OnItemListener? = null
    ): ListAdapter<Deal, HomeRecyclerAdapter.ViewHolder>(HomeDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)

        holder.bind(item)
    }

    class ViewHolder private constructor(
        private val binding: ItemDealBinding,
        private val listener: OnItemListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Deal) {

            binding.dealItem = item
            binding.itemDealConstrainLayout.setOnClickListener {
                listener?.onItemClick(adapterPosition, item)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(
                parent: ViewGroup,
                listener: OnItemListener?
            ): ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ItemDealBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, listener)
            }
        }
    }

    interface OnItemListener {

        fun onItemClick(position: Int, deal: Deal)
    }

    class HomeDiffCallback : DiffUtil.ItemCallback<Deal>() {
        override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
            return oldItem == newItem
        }
    }
}