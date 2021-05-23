package com.carnava.android.cart.presentation

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.RemoveProductFromCartUseCase
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.core.utils.EventBus
import com.carnava.android.databinding.FragmentCartBinding
import com.carnava.android.favorite.domain.usecases.AddIntoFavoriteUseCase
import com.carnava.android.favorite.domain.usecases.RemoveFromFavoriteUseCase
import com.carnava.android.product.domain.models.ProductModel
import kotlinx.coroutines.launch
import timber.log.Timber

class CartFragment : BaseFragment(R.layout.fragment_cart) {

    private val cart = mutableListOf<ProductModel>()

    private lateinit var binding: FragmentCartBinding

    override fun setupView(view: View) {
        binding = FragmentCartBinding.bind(view)
        with(binding) {

            App.eventBus.setEventListener<ProductModel>(
                this,
                EventBus.Events.ADD_TO_CART
            ) {
                cart.add(it)
                (productsCartList.adapter as? CartAdapter)
                    ?.submitList(cart)
            }

            App.eventBus.setEventListener<ProductModel>(
                this,
                EventBus.Events.REMOVE_FROM_CART
            ) {
                cart.remove(it)
                (productsCartList.adapter as? CartAdapter)
                    ?.submitList(cart)
            }

            App.eventBus.setEventListener<ProductModel>(
                this,
                EventBus.Events.ADD_FAVORITE
            ) {
                cart[cart.indexOf(it)] = it
                (productsCartList.adapter as? CartAdapter)
                    ?.submitList(cart)
            }

            App.eventBus.setEventListener<ProductModel>(
                this,
                EventBus.Events.REMOVE_FAVORITE
            ) {
                cart[cart.indexOf(it)] = it
                (productsCartList.adapter as? CartAdapter)
                    ?.submitList(cart)
            }

            productsCartList.adapter = CartAdapter(
                removeFromCartClickListener = {
                    lifecycleScope.launch {
                        try {
                            RemoveProductFromCartUseCase().invoke(it)
                        } catch (e: Exception) {
                            Timber.e(e)
                        }
                    }
                },
                favoriteClickListener = { product, check ->
                    lifecycleScope.launch {
                        try {
                            if (check) AddIntoFavoriteUseCase().invoke(product)
                            else RemoveFromFavoriteUseCase().invoke(product)
                        } catch (e: Exception) {
                            Timber.e(e)
                        }
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        App.eventBus.removeEventListeners(this)
        super.onDestroyView()
    }
}