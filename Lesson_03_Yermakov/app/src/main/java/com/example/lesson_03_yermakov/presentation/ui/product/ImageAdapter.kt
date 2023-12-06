package com.example.lesson_03_yermakov.presentation.ui.product

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson_03_yermakov.R
import com.google.android.material.imageview.ShapeableImageView

class ImageAdapter(private val clickListener: (ShopImage) -> Unit) :
    ListAdapter<ShopImage, ImageAdapter.ShopImageViewHolder>(DIFF_UTIL) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ShopImage>() {

            override fun areItemsTheSame(
                oldItem: ShopImage, newItem: ShopImage
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ShopImage, newItem: ShopImage
            ): Boolean = oldItem.imageUrl == newItem.imageUrl

        }
    }

    inner class ShopImageViewHolder(
        view: View
    ) :
        RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ShapeableImageView>(R.id.image)
        private val stroke = view.findViewById<ShapeableImageView>(R.id.stroke)

        fun bind(item: ShopImage, clickListener: (ShopImage) -> Unit) {
            if (item.imageUrl.isNotEmpty()) {
                Glide.with(imageView).load(item.imageUrl).into(imageView)
            } else {
                imageView.setImageResource(R.drawable.ic_error_logo)
            }
            if (item.isSelected) {
                stroke.strokeColor = ColorStateList.valueOf(Color.parseColor("grey"))
            } else {
                stroke.strokeColor = ColorStateList.valueOf(Color.parseColor("white"))
            }
            itemView.setOnClickListener {
                clickListener(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopImageViewHolder =
        ShopImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        )

    override fun onBindViewHolder(holder: ShopImageViewHolder, position: Int) {
        holder.bind(currentList[position], clickListener)
    }

}