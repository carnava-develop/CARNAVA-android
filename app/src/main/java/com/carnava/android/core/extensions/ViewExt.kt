package com.carnava.android.core.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.core.view.isVisible

fun View.isVisibleWithAnimation(isVisible: Boolean, doAfterAnimation: (() -> Unit)? = null) {
    this.animate()
        .setDuration(300L)
        .alpha(if (isVisible) 1.0f else 0.0f)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                this@isVisibleWithAnimation.isVisible = isVisible
                doAfterAnimation?.invoke()
            }
        })
}