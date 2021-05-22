package com.carnava.android.home.presentation

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun setupView(view: View) {
        binding = FragmentHomeBinding.bind(view)
        with(binding) {
            homeList.adapter = CategoryAdapter {

            }

            lifecycleScope.launch(Dispatchers.IO) {
                val categories = App.categoryRepository.loadCategories()
                withContext(Dispatchers.Main) {
                    (homeList.adapter as? CategoryAdapter)
                        ?.submitList(categories)
                }
            }
        }
    }
}