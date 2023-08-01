package com.example.androidtask.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson

fun FragmentActivity.replaceFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment, fragment.javaClass.simpleName)
        .apply {
            if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        }
        .commit()
}

fun <T> Bundle.putDataObject(key: String, t: T) {
    putString(key, Gson().toJson(t))
}

fun <T> Bundle.getDataObjectExtra(key: String, type: Class<T>): T {
    return Gson().fromJson(getString(key), type)
}