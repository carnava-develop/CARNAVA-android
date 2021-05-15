package com.carnava.android.core.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class IdentificationDiffUtilCallback<T : Identification> : DiffUtil.ItemCallback<T>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.identification == newItem.identification
}