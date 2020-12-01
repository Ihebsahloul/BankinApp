
package com.bankin.task.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.bankin.task.R
import com.bankin.task.Utilities.FontType
import com.bankin.task.Utilities.getTypeface
import com.google.android.material.button.MaterialButton

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialButton(context, attrs, defStyleAttr) {

    init {
        if (attrs != null) {
            @SuppressLint("CustomViewStyleable")
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFont)
            val textStyle = typedArray.getInt(R.styleable.CustomFont_textFont, 0)
            withCustomFont(context, FontType.from(textStyle))
            typedArray.recycle()
        }
    }

    private fun withCustomFont(context: Context, textFont: FontType) {
        typeface = when (textFont) {
            FontType.ROBOTO_LIGHT -> getTypeface(
                context.getString(R.string.roboto_light),
                R.font.googlesans_regular,
                context
            )
            FontType.ROBOTO_REGULAR -> getTypeface(
                context.getString(R.string.roboto_regular),
                R.font.googlesans_regular,
                context
            )
            FontType.ROBOTO_MEDIUM -> getTypeface(
                context.getString(R.string.roboto_medium),
                R.font.googlesans_medium,
                context
            )
            FontType.ROBOTO_BOLD -> getTypeface(
                context.getString(R.string.roboto_bold),
                R.font.googlesans_bold,
                context
            )
        }
    }
}