package com.carnava.android.favorite.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.AddProductInCartUseCase
import com.carnava.android.core.extensions.toDpInt
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.core.ui.SpaceItemDecoration
import com.carnava.android.core.utils.EventBus
import com.carnava.android.databinding.FragmentFavoriteBinding
import com.carnava.android.favorite.domain.usecases.LoadFavoritesProductsUseCase
import com.carnava.android.favorite.domain.usecases.RemoveFromFavoriteUseCase
import com.carnava.android.product.domain.models.ProductModel
import com.carnava.android.product.presentation.ProductAdapter
import kotlinx.coroutines.launch

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private var favorites = mutableListOf<ProductModel>()

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteItemDecoration by lazy { SpaceItemDecoration(16.toDpInt()) }
    private val favoriteAdapter by lazy {
        ProductAdapter(
            addToCartClickListener = {
                lifecycleScope.launch {
                    AddProductInCartUseCase().invoke(it)
                }
            },
            favoriteClickListener = { product, check ->
                lifecycleScope.launch {
                    try {
                        RemoveFromFavoriteUseCase().invoke(product)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.eventBus.setEventListener<ProductModel>(
            idObserver = this@FavoriteFragment,
            idAction = EventBus.Events.ADD_FAVORITE
        ) { product ->
            favorites.add(product)
            favoriteAdapter.submitList(favorites)
        }
        App.eventBus.setEventListener<ProductModel>(
            idObserver = this@FavoriteFragment,
            idAction = EventBus.Events.REMOVE_FAVORITE
        ) { product ->
            favorites.remove(product)
            favoriteAdapter.submitList(favorites)
        }
    }

    override fun setupView(view: View) {
        binding = FragmentFavoriteBinding.bind(view)
        with(binding) {
            favoriteProductsList.addItemDecoration(favoriteItemDecoration)
            favoriteProductsList.adapter = favoriteAdapter

            if (favorites.isEmpty()) {
                lifecycleScope.launch {
                    favorites = LoadFavoritesProductsUseCase().invoke().toMutableList()
                    favoriteAdapter.submitList(favorites)
                }
            }
        }
    }

    override fun onDestroyView() {
        with(binding) {
            favoriteProductsList.removeItemDecoration(favoriteItemDecoration)
            favoriteProductsList.adapter = null
        }
        super.onDestroyView()
    }

    override fun onDestroy() {
        App.eventBus.removeEventListeners(this)
        super.onDestroy()
    }
}