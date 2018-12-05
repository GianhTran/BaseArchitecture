package com.bonseyjaden.basearchitecture.data.repository.local.api

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefApi(context: Context, private val gson: Gson) {
    private val sharedPreferences: SharedPreferences

    init {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit();
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
            else -> editor.putString(key, gson.toJson(data))
        }
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, clazz: Class<T>): T? {
        if (!sharedPreferences.contains(key)) {
            return null
        }
        return when (clazz) {
            String::class.java -> sharedPreferences.getString(key, "") as T
            Boolean::class.java -> java.lang.Boolean.valueOf(
                sharedPreferences.getBoolean(key, false)
            ) as T
            Float::class.java -> java.lang.Float.valueOf(sharedPreferences.getFloat(key, 0f)) as T
            Int::class.java -> Integer.valueOf(sharedPreferences.getInt(key, 0)) as T
            Long::class.java -> java.lang.Long.valueOf(sharedPreferences.getLong(key, 0)) as T
            else -> gson.fromJson(sharedPreferences.getString(key, ""), clazz)
        }
    }

    fun <T> putList(key: String, list: List<T>) {
        val editor = sharedPreferences.edit()
        editor.putString(key, gson.toJson(list))
        editor.apply()
    }

    fun <T> getList(key: String, clazz: Class<T>): List<T>? {
        val typeOfT = TypeToken.getParameterized(List::class.java, clazz).type
        return gson.fromJson<List<T>>(get(key, String::class.java), typeOfT)
    }

    fun removeKey(key: String) {
        sharedPreferences.edit().let {
            it.remove(key)
            it.apply()
        }
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "AzuiSharedPreferences"
        const val PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN"
        const val PREF_USER = "PREF_USER"
    }
}