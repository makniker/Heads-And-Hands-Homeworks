package com.example.lesson_03_yermakov.presentation.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_03_yermakov.R

class SizeAdapter(private val clickListener: (String) -> Unit) :
    ListAdapter<String, SizeAdapter.SizeViewHolder>(DIFF_UTIL) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(
                oldItem: String, newItem: String
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: String, newItem: String
            ): Boolean = oldItem == newItem

        }
    }

    inner class SizeViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val size = view.findViewById<TextView>(R.id.size)
        fun bind(item: String, clickListener: (String) -> Unit) {
            size.text = item
            itemView.setOnClickListener { clickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder =
        SizeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_size, parent, false)
        )

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.bind(currentList[position], clickListener)
    }
}