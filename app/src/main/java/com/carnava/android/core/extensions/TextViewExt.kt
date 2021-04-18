package com.carnava.android.core.extensions

import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.text.set
import androidx.core.text.toSpannable

fun TextView.createLink(
    fullText: String,
    partTextToClick: String,
    @ColorRes color: Int = 0,
    clickAction: () -> Unit
) {
    val startPosition = fullText.indexOf(partTextToClick)
    val endPosition: Int = fullText.lastIndexOf(partTextToClick) + partTextToClick.length

    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            clickAction.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            val linkColor = context.color(color)
            ds.color = linkColor
            ds.isUnderlineText = false
        }
    }

    val spannableString = fullText.toSpannable()
    spannableString[startPosition, endPosition] = clickableSpan

    text = spannableString
    movementMethod = LinkMovementMethod.getInstance()
}