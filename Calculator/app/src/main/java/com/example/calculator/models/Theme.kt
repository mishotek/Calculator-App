package com.example.calculator.models

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.calculator.R


class Theme(private val activity: Activity) {

    private val themeKey = "app_theme_key";
    private val sharedPref: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)

    fun applyTheme() {
        if (getCurrTheme() == ThemeType.LIGHT) {
            activity.setTheme(R.style.LightTheme)
        } else {
            activity.setTheme(R.style.DarkTheme)
        }
    }

    fun toggleTheme() {
        var newTheme = if (getCurrTheme() == ThemeType.LIGHT) {
            ThemeType.DARK
        } else {
            ThemeType.LIGHT
        }

        with (sharedPref.edit()) {
            putString(themeKey, newTheme)
            commit()
        }

        activity.finish();
        activity.startActivity(Intent(activity, activity.javaClass))
    }

    private fun getCurrTheme(): String {
        return sharedPref.getString(themeKey, ThemeType.LIGHT) ?: return ThemeType.LIGHT
    }
}