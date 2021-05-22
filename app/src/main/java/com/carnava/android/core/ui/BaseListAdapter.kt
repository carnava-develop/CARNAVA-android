package com.carnava.android.core.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {
    override fun submitList(list: MutableList<T>?) = super.submitList(list?.toList())
    fun submitList(list: List<T>?, i: Int = 0) = super.submitList(list?.toList())
}