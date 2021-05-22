package com.carnava.android.core.extensions

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun Context.verticalLayoutManager() = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
fun RecyclerView.verticalLayoutManager() = this.context?.verticalLayoutManager()