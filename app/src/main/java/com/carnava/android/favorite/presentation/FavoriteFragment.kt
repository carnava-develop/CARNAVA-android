package com.carnava.android.favorite.presentation

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.AddProductInCartUseCase
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.databinding.FragmentFavoriteBinding
import com.carnava.android.favorite.domain.usecases.AddIntoFavoriteUseCase
import com.carnava.android.favorite.domain.usecases.LoadFavoritesProductsUseCase
import com.carnava.android.favorite.domain.usecases.RemoveFromFavoriteUseCase
import kotlinx.coroutines.launch

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding

    override fun setupView(view: View) {
        binding = FragmentFavoriteBinding.bind(view)
        with(binding) {
            favoriteProductsList.adapter = FavoriteProductsAdapter(
                addToCartClickListener = {
                    AddProductInCartUseCase().invoke(it)
                }, favoriteClickListener = { productModel, check ->
                    lifecycleScope.launch {
                        try {
                            if (check) AddIntoFavoriteUseCase().invoke(productModel)
                            else RemoveFromFavoriteUseCase().invoke(productModel)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            )

            lifecycleScope.launch {
                val favoriteProducts = LoadFavoritesProductsUseCase().invoke().toMutableList()
                (favoriteProductsList.adapter as? FavoriteProductsAdapter)
                    ?.submitList(favoriteProducts)
            }
        }
    }
}