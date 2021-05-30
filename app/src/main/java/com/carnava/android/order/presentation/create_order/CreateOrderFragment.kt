package com.carnava.android.order.presentation.create_order

import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.cart.domain.usecases.ClearCartUseCase
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.databinding.FragmentCreateOrderBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateOrderFragment : BaseFragment(R.layout.fragment_create_order) {

    private lateinit var binding: FragmentCreateOrderBinding
    override fun setupView(view: View) {
        binding = FragmentCreateOrderBinding.bind(view)
        with(binding) {
            placeOrderCreateOrderButton.setOnClickListener {
                when {
                    addressCreateOrderInput.text.isBlank() -> {
                    }
                    phoneCreateOrderInput.text.isBlank() -> {
                    }
                    else -> lifecycleScope.launch {
                        ClearCartUseCase().invoke()
                        Toast.makeText(
                            ctx,
                            getString(R.string.create_order_place_order_success),
                            Toast.LENGTH_SHORT
                        ).show()
                        delay(3000)
                        App.navigator.goBack()
                    }
                }
            }
        }
    }
}