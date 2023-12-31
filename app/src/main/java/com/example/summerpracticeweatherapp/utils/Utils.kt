package com.example.summerpracticeweatherapp.utils

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.chrono.IsoChronology

fun EditText.setOnDebounceTextChanged(
    coroutineScope: CoroutineScope,
    onTextChanged: (String) -> Unit
) {
    this.addTextChangedListener {
        coroutineScope.launch(Dispatchers.Main) {
            if (it.toString().isEmpty()) return@launch
            delay(100)
            onTextChanged(it.toString())
        }
    }
}

object SharedPrefsUtils {
    private const val APP_PREFS = "APP_PREFS"
    private const val CITY_NAME = "CITY_NAME"
    private const val CITY_DEFAULT_VALUE = "Moscow"

    private const val IS_OPENED = "OPENED"
    private const val IS_OPENED_DEFAULT_VALUE = false

    fun isOpened(ctx: Context) : Boolean{
        with(ctx.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)) {
            return getBoolean(IS_OPENED, IS_OPENED_DEFAULT_VALUE)
        }
    }

    fun setOpenState(ctx: Context){
        with(ctx.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE).edit()) {
            putBoolean(IS_OPENED, true)
            commit()
        }
    }

    fun saveCity(ctx: Context, city: String) {
        with(ctx.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE).edit()) {
            putString(CITY_NAME, city)
            commit()
        }
    }

    fun getSavedCity(ctx: Context): String {
        with(ctx.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)) {
            return getString(CITY_NAME, CITY_DEFAULT_VALUE) ?: CITY_DEFAULT_VALUE
        }
    }
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}