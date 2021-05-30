package com.carnava.android.catalog.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.AddProductInCartUseCase
import com.carnava.android.core.extensions.toDpInt
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.core.ui.SpaceItemDecoration
import com.carnava.android.core.utils.EventBus
import com.carnava.android.databinding.FragmentCatalogBinding
import com.carnava.android.favorite.domain.usecases.AddIntoFavoriteUseCase
import com.carnava.android.favorite.domain.usecases.LoadFavoritesProductsUseCase
import com.carnava.android.favorite.domain.usecases.RemoveFromFavoriteUseCase
import com.carnava.android.product.domain.models.ProductModel
import com.carnava.android.product.domain.usecases.LoadProductsByCategoryUseCase
import com.carnava.android.product.presentation.ProductAdapter
import kotlinx.coroutines.launch
import timber.log.Timber

class CatalogFragment : BaseFragment(R.layout.fragment_catalog) {

    private val screenArg: Screens.Catalog by lazy { App.screenResolver.getScreen(this) }
    private val idCategoryArg by lazy { screenArg.idCategory }

    private val products = mutableListOf<ProductModel>()

    private lateinit var binding: FragmentCatalogBinding
    private val productAdapter by lazy {
        ProductAdapter(
            addToCartClickListener = {
                lifecycleScope.launch {
                    AddProductInCartUseCase().invoke(it)
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
            idObserver = this@CatalogFragment,
            idAction = EventBus.Events.ADD_FAVORITE
        ) { product ->
            try {
                val index = products.indexOfFirst { it.identification == product.identification }
                products[index] = product.copy(isFavorite = true)
            } catch (e: IndexOutOfBoundsException) {
                Timber.e(e)
            }
            productAdapter.submitList(products)
        }
        App.eventBus.setEventListener<ProductModel>(
            idObserver = this@CatalogFragment,
            idAction = EventBus.Events.REMOVE_FAVORITE
        ) { product ->
            try {
                val index = products.indexOfFirst { it.identification == product.identification }
                products[index] = product.copy(isFavorite = false)
            } catch (e: IndexOutOfBoundsException) {
                Timber.e(e)
            }
            productAdapter.submitList(products)
        }
    }

    override fun setupView(view: View) {
        binding = FragmentCatalogBinding.bind(view)
        with(binding) {
            productsCatalogList.addItemDecoration(SpaceItemDecoration(16.toDpInt()))
            productsCatalogList.adapter = productAdapter

            if (products.isEmpty()) {
                lifecycleScope.launch {
                    val productsCategory = LoadProductsByCategoryUseCase().invoke(idCategoryArg)
                    val productsFavorite = LoadFavoritesProductsUseCase().invoke()
                    productsCategory.forEach { product ->
                        products.add(productsFavorite.find {
                            product.identification == it.identification
                        } ?: product)
                    }
                    productAdapter.submitList(products)
                }
            }
        }
    }

    override fun onDestroy() {
        App.eventBus.removeEventListeners(this)
        binding.productsCatalogList.adapter = null
        super.onDestroy()
    }
}