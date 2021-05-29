package com.carnava.android.catalog.presentation

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.core.extensions.toDpInt
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.core.ui.SpaceItemDecoration
import com.carnava.android.databinding.FragmentCatalogBinding
import com.carnava.android.product.domain.usecases.LoadProductsByCategoryUseCase
import com.carnava.android.product.presentation.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogFragment : BaseFragment(R.layout.fragment_catalog) {

    private val screenArg: Screens.Catalog by lazy { App.screenResolver.getScreen(this) }
    private val idCategoryArg by lazy { screenArg.idCategory }

    private lateinit var binding: FragmentCatalogBinding
    override fun setupView(view: View) {
        binding = FragmentCatalogBinding.bind(view)
        with(binding) {
            productsCatalogList.addItemDecoration(SpaceItemDecoration(16.toDpInt()))
            productsCatalogList.adapter = ProductAdapter(
                addToCartClickListener = {

                },
                favoriteClickListener = { productModel, check ->

                }
            )

            lifecycleScope.launch(Dispatchers.IO) {
                val products = LoadProductsByCategoryUseCase().invoke(idCategoryArg)
                withContext(Dispatchers.Main) {
                    (productsCatalogList.adapter as? ProductAdapter)
                        ?.submitList(products)
                }
            }
        }
    }
}