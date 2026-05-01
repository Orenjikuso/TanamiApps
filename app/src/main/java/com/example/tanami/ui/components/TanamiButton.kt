package com.example.tanami.ui.components // Sesuaikan dengan nama package aslimu ya

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.tanami.R

class TanamiButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    init {
        // Memanggil desain visual yang baru saja kita buat
        background = ContextCompat.getDrawable(context, R.drawable.bg_tanami_button)
        // Set warna teks jadi putih agar kontras dengan hijau
        setTextColor(ContextCompat.getColor(context, R.color.neutral_50))
        // Mematikan huruf kapital otomatis bawaan Android
        isAllCaps = false
    }
}