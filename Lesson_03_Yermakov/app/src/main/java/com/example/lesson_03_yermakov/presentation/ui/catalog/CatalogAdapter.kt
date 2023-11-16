package com.example.lesson_03_yermakov.presentation.ui.catalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson_03_yermakov.R
import com.google.android.material.imageview.ShapeableImageView
import javax.inject.Inject

class CatalogAdapter @Inject constructor() :
    ListAdapter<UIModelCatalogProduct, CatalogAdapter.ProductViewHolder>(DIFF_UTIL) {
    private lateinit var context: Context

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<UIModelCatalogProduct>() {

            override fun areItemsTheSame(
                oldItem: UIModelCatalogProduct,
                newItem: UIModelCatalogProduct
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UIModelCatalogProduct,
                newItem: UIModelCatalogProduct
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.findViewById<ShapeableImageView>(R.id.product_image)
        private val title = view.findViewById<TextView>(R.id.title)
        private val department = view.findViewById<TextView>(R.id.department)
        private val price = view.findViewById<TextView>(R.id.price)

        fun bind(item: UIModelCatalogProduct) {
            title.text = item.title
            department.text = item.department
            price.text = context.getString(R.string.price_format, item.price.toString())
            imageView.maxHeight = imageView.maxWidth
            Glide.with(imageView)
                .load(item.preview)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_catalog, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
