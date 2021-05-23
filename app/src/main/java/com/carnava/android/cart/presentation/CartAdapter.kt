package com.carnava.android.cart.presentation

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.carnava.android.R
import com.carnava.android.core.ui.BaseListAdapter
import com.carnava.android.core.ui.BaseViewHolder
import com.carnava.android.core.utils.IdentificationDiffUtilCallback
import com.carnava.android.databinding.ItemProductBinding
import com.carnava.android.product.domain.models.ProductModel

class CartAdapter(
    private val removeFromCartClickListener: (ProductModel) -> Unit,
    private val favoriteClickListener: (ProductModel, check: Boolean) -> Unit

) : BaseListAdapter<ProductModel, CartAdapter.ProductViewHolder>(IdentificationDiffUtilCallback()) {

    inner class ProductViewHolder(parent: ViewGroup) :
        BaseViewHolder<ProductModel>(R.layout.item_product, parent) {

        private lateinit var currentItem: ProductModel

        private val binding = ItemProductBinding.bind(itemView)

        init {
            with(binding) {
                addToCartItemProductButton.setOnClickListener {
                    removeFromCartClickListener.invoke(currentItem)
                }
                favoriteItemProductImage.setOnClickListener {
                    favoriteClickListener.invoke(currentItem, !currentItem.isFavorite)
                }
            }
        }

        override fun bind(item: ProductModel) {
            with(binding) {
                currentItem = item
                titleItemProductText.text = item.title
                addToCartItemProductButton.text = ctx.getString(R.string.cart_remove)
                Glide.with(ctx).load(item.image).into(imageItemProduct)
                Glide.with(ctx).load(
                    if (item.isFavorite) R.drawable.ic_favorite_active
                    else R.drawable.ic_favorite_passive
                ).into(favoriteItemProductImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(parent)
    override fun onBindViewHolder(holder: ProductViewHolder, pos: Int) = holder.bind(getItem(pos))
}