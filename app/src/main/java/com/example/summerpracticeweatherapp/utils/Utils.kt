package com.example.summerpracticeweatherapp.utils

import android.content.Context
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.summerpracticeweatherapp.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun EditText.setOnDebounceTextChanged(
    coroutineScope: CoroutineScope,
    onTextChanged: (String) -> Unit
) {
    this.addTextChangedListener {
        coroutineScope.launch(Dispatchers.Main) {
            if (it.toString().isEmpty()) return@launch
            delay(1000)
            onTextChanged(it.toString())
        }
    }
}

object SharedPrefsUtils {
    private const val APP_PREFS = "APP_PREFS"
    private const val CITY_NAME = "CITY_NAME"
    private const val CITY_DEFAULT_VALUE = "Moscow"

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