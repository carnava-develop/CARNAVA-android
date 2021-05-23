package com.carnava.android.favorite.presentation

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.AddProductInCartUseCase
import com.carnava.android.core.ui.BaseFragment
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

    override fun setupView(view: View) {
        binding = FragmentFavoriteBinding.bind(view)
        with(binding) {
            App.eventBus.setEventListener<ProductModel>(
                idObserver = this@FavoriteFragment,
                idAction = EventBus.Events.ADD_FAVORITE
            ) { product ->
                favorites.add(product)
                (favoriteProductsList.adapter as? ProductAdapter)
                    ?.submitList(favorites)
            }

            App.eventBus.setEventListener<ProductModel>(
                idObserver = this@FavoriteFragment,
                idAction = EventBus.Events.REMOVE_FAVORITE
            ) { product ->
                favorites.remove(product)
                (favoriteProductsList.adapter as? ProductAdapter)
                    ?.submitList(favorites)
            }


            favoriteProductsList.adapter = ProductAdapter(
                addToCartClickListener = {
                    AddProductInCartUseCase().invoke(it)
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

            lifecycleScope.launch {
                favorites = LoadFavoritesProductsUseCase().invoke().toMutableList()
                (favoriteProductsList.adapter as? ProductAdapter)
                    ?.submitList(favorites)
            }
        }
    }
}