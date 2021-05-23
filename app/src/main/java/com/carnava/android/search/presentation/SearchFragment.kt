package com.carnava.android.search.presentation

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.R
import com.carnava.android.core.extensions.setDoneClickListener
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.databinding.FragmentSearchBinding
import com.carnava.android.product.presentation.ProductAdapter
import com.carnava.android.search.domain.SearchUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    override fun setupView(view: View) {
        binding = FragmentSearchBinding.bind(view)
        with(binding) {
            searchInput.setDoneClickListener {
                lifecycleScope.launch {
                    try {
                        val searchResult = SearchUseCase().invoke(it.text.toString())
                        (resultSearchList.adapter as? ProductAdapter)
                            ?.submitList(searchResult)
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }
            resultSearchList.adapter = ProductAdapter(
                addToCartClickListener = {

                },
                favoriteClickListener = { product, check ->

                }
            )
        }
    }
}