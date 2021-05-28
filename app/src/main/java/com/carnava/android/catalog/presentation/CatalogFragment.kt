package com.carnava.android.catalog.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.databinding.FragmentCatalogBinding
import com.carnava.android.product.domain.usecases.LoadProductByCategory
import com.carnava.android.product.presentation.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogFragment : BaseFragment(R.layout.fragment_catalog) {

    private val screenArg: Screens.Catalog by lazy { App.screenResolver.getScreen(this) }
    private val idCategoryArg by lazy { screenArg.idCategory }

    private lateinit var binding: FragmentCatalogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
        with(binding) {
            productsCatalogList.adapter = ProductAdapter(
                addToCartClickListener = {

                },
                favoriteClickListener = { productModel, check ->

                }
            )

            lifecycleScope.launch(Dispatchers.IO) {
                val products = LoadProductByCategory().invoke(idCategoryArg)
                withContext(Dispatchers.Main) {
                    (productsCatalogList.adapter as? ProductAdapter)
                        ?.submitList(products)
                }
            }
        }
    }
}