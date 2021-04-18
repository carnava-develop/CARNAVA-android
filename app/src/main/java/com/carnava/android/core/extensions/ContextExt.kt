package com.carnava.android.core.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


fun Context.color(@ColorRes resId: Int): Int = ContextCompat.getColor(this, resId)

fun Context.drawable(@DrawableRes resId: Int): Drawable = ContextCompat.getDrawable(this, resId)!!
