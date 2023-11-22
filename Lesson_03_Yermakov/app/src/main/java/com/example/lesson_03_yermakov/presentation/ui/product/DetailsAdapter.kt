package com.example.lesson_03_yermakov.presentation.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_03_yermakov.R
import javax.inject.Inject

class DetailsAdapter @Inject constructor() :
    ListAdapter<String, DetailsAdapter.StringViewHolder>(DIFF_UTIL) {
    @Inject
    lateinit var context: Context

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(
                oldItem: String, newItem: String
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: String, newItem: String
            ): Boolean =
                oldItem == newItem
        }
    }

    inner class StringViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(str: String) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder =
        StringViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_details, parent, false)
        )

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}