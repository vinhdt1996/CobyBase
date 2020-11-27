package com.coby.cobybase.ui.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coby.cobybase.databinding.ItemTestBinding
import com.coby.cobybase.model.TestModel

class GifTabAdapter(
) :
    ListAdapter<TestModel, GifTabAdapter.GifTabViewHolder>(GifTabDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifTabViewHolder {
        return GifTabViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GifTabViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GifTabViewHolder private constructor(val binding: ItemTestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TestModel) {
            binding.tvTitle.text = item.title
        }

        companion object {
            fun from(parent: ViewGroup): GifTabViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemTestBinding.inflate(layoutInflater, parent, false)
                return GifTabViewHolder(binding)
            }
        }

    }

    class GifTabDiffCallback : DiffUtil.ItemCallback<TestModel>() {
        override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }
    }

}