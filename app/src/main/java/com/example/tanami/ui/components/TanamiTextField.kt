package com.example.tanami.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.tanami.R

class TanamiTextField @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        // Memanggil desain kotak border tadi
        background = ContextCompat.getDrawable(context, R.drawable.bg_tanami_textfield)
        // Set warna teks yang diketik user
        setTextColor(ContextCompat.getColor(context, R.color.neutral_700))
        // Set warna hint (teks bayangan sebelum diketik)
        setHintTextColor(ContextCompat.getColor(context, R.color.neutral_400))
    }
}