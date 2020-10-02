package com.coby.cobybase.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.coby.cobybase.R
import com.coby.cobybase.databinding.ItemFeedBinding
import com.coby.cobybase.model.Feed

class FeedPagingAdapter() : PagedListAdapter<Feed, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Feed>() {
            override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemFeedBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_feed,
            parent,
            false
        )
        return ItemFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemFeedViewHolder)?.bind(getItem(position), position)
    }

    inner class ItemFeedViewHolder(private val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feed: Feed?, position: Int) {
            binding.tvTitle.text = "$position"
        }
    }
}