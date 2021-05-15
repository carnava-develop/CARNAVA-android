package com.carnava.android.core.ui

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.carnava.android.core.extensions.inflate

abstract class BaseViewHolder<T> : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView)

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup) : super(parent.inflate(layoutRes))

    protected val ctx: Context by lazy { itemView.context }
    protected val res: Resources by lazy { ctx.resources }

    open fun bind(item: T) {

    }
}