package com.carnava.android.favorite.presentation

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.carnava.android.R
import com.carnava.android.core.ui.BaseListAdapter
import com.carnava.android.core.ui.BaseViewHolder
import com.carnava.android.core.utils.IdentificationDiffUtilCallback
import com.carnava.android.databinding.ItemProductBinding
import com.carnava.android.product.domain.models.ProductModel

class FavoriteProductsAdapter(
    private val addToCartClickListener: (ProductModel) -> Unit,
    private val favoriteClickListener: (ProductModel) -> Unit
) : BaseListAdapter<ProductModel, FavoriteProductsAdapter.ProductViewHolder>(
    IdentificationDiffUtilCallback()
) {

    inner class ProductViewHolder(parent: ViewGroup) :
        BaseViewHolder<ProductModel>(R.layout.item_product, parent) {

        private val binding = ItemProductBinding.bind(itemView)
        private lateinit var currentItem: ProductModel

        init {
            with(binding) {
                addToCartItemProductButton.setOnClickListener {
                    addToCartClickListener.invoke(currentItem)
                }
                favoriteItemProductImage.setOnClickListener {
                    favoriteClickListener.invoke(currentItem)
                }
            }
        }

        override fun bind(item: ProductModel) {
            with(binding) {
                currentItem = item
                titleItemProductText.text = item.title
                Glide.with(ctx).load(item.image).into(imageItemProduct)
                Glide.with(ctx).load(R.drawable.ic_favorite_active).into(favoriteItemProductImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(parent)
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))
}