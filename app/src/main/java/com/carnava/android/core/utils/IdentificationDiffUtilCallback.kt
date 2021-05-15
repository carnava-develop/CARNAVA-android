package com.carnava.android.core.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class IdentificationDiffUtilCallback<T : Identification> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}