package com.carnava.android.search.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.AddProductInCartUseCase
import com.carnava.android.core.extensions.setDoneClickListener
import com.carnava.android.core.extensions.toDpInt
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.core.ui.SpaceItemDecoration
import com.carnava.android.core.utils.EventBus
import com.carnava.android.databinding.FragmentSearchBinding
import com.carnava.android.favorite.domain.usecases.AddIntoFavoriteUseCase
import com.carnava.android.favorite.domain.usecases.RemoveFromFavoriteUseCase
import com.carnava.android.product.domain.models.ProductModel
import com.carnava.android.product.presentation.ProductAdapter
import com.carnava.android.search.domain.SearchUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private val searchResult = mutableListOf<ProductModel>()

    private lateinit var binding: FragmentSearchBinding
    private val productSpaceItemDecoration by lazy { SpaceItemDecoration(16.toDpInt()) }
    private val productAdapter by lazy {
        ProductAdapter(
            addToCartClickListener = { product ->
                lifecycleScope.launch {
                    AddProductInCartUseCase().invoke(product)
                }
            },
            favoriteClickListener = { product, check ->
                lifecycleScope.launch {
                    if (check) AddIntoFavoriteUseCase().invoke(product)
                    else RemoveFromFavoriteUseCase().invoke(product)
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.eventBus.setEventListener<ProductModel>(
            idObserver = this,
            idAction = EventBus.Events.ADD_FAVORITE
        ) { product ->
            try {
                val index =
                    searchResult.indexOfFirst { it.identification == product.identification }
                searchResult[index] = product.copy(isFavorite = true)
            } catch (e: IndexOutOfBoundsException) {
                Timber.e(e)
            }
            productAdapter.submitList(searchResult)
        }
        App.eventBus.setEventListener<ProductModel>(
            idObserver = this,
            idAction = EventBus.Events.REMOVE_FAVORITE
        ) { product ->
            try {
                val index =
                    searchResult.indexOfFirst { it.identification == product.identification }
                searchResult[index] = product.copy(isFavorite = false)
            } catch (e: IndexOutOfBoundsException) {
                Timber.e(e)
            }
            productAdapter.submitList(searchResult)
        }
    }

    override fun setupView(view: View) {
        binding = FragmentSearchBinding.bind(view)
        with(binding) {
            resultSearchList.addItemDecoration(productSpaceItemDecoration)
            resultSearchList.adapter = productAdapter

            searchInput.setDoneClickListener {
                lifecycleScope.launch {
                    try {
                        searchResult.clear()
                        searchResult.addAll(SearchUseCase().invoke(it.text.toString()))
                        productAdapter.submitList(searchResult)
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        with(binding) {
            resultSearchList.removeItemDecoration(productSpaceItemDecoration)
            resultSearchList.adapter = null
        }
        super.onDestroyView()
    }

    override fun onDestroy() {
        App.eventBus.removeEventListeners(this)
        super.onDestroy()
    }
}