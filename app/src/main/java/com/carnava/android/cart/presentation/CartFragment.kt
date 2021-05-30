package com.carnava.android.cart.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.RemoveProductFromCartUseCase
import com.carnava.android.core.extensions.toDpInt
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.core.ui.SpaceItemDecoration
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
    private val cartItemDecoration by lazy { SpaceItemDecoration(16.toDpInt()) }
    private val cartAdapter by lazy {
        CartAdapter(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.eventBus.setEventListener<ProductModel>(
            this,
            EventBus.Events.ADD_TO_CART
        ) { product ->
            cart.add(product)
            cartAdapter.submitList(cart)
        }
        App.eventBus.setEventListener<ProductModel>(
            this,
            EventBus.Events.REMOVE_FROM_CART
        ) { product ->
            try {
                val index = cart.indexOfFirst { it.identification == product.identification }
                cart.removeAt(index)
                cartAdapter.submitList(cart)
            } catch (e: IndexOutOfBoundsException) {
                Timber.e(e)
            }
        }
        App.eventBus.setEventListener<ProductModel>(
            this,
            EventBus.Events.ADD_FAVORITE
        ) { product ->
            try {
                val index = cart.indexOfFirst { it.identification == product.identification }
                cart[index] = product.copy(isFavorite = true)
                cartAdapter.submitList(cart)
            } catch (e: IndexOutOfBoundsException) {
                Timber.e(e)
            }
        }
        App.eventBus.setEventListener<ProductModel>(
            this,
            EventBus.Events.REMOVE_FAVORITE
        ) { product ->
            try {
                val index = cart.indexOfFirst { it.identification == product.identification }
                cart[index] = product.copy(isFavorite = false)
                cartAdapter.submitList(cart)
            } catch (e: IndexOutOfBoundsException) {
                Timber.e(e)
            }
        }
    }

    override fun setupView(view: View) {
        binding = FragmentCartBinding.bind(view)
        with(binding) {
            productsCartList.addItemDecoration(cartItemDecoration)
            productsCartList.adapter = cartAdapter
        }
    }

    override fun onDestroyView() {
        with(binding.productsCartList) {
            removeItemDecoration(cartItemDecoration)
            adapter = null
        }
        super.onDestroyView()
    }

    override fun onDestroy() {
        App.eventBus.removeEventListeners(this)
        super.onDestroy()
    }
}