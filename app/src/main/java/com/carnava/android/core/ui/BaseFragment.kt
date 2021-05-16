package com.carnava.android.core.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.carnava.android.App

abstract class BaseFragment : Fragment {
    constructor(@LayoutRes layoutId: Int) : super(layoutId)
    constructor() : super()

    protected val ctx by lazy { requireContext() }
    protected val res: Resources by lazy { ctx.resources }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    open fun setupView(view: View) {}

    override fun onDestroy() {
        App.eventBus.removeEventListeners(this)
        super.onDestroy()
    }
}
