/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.pinaki.desipe.data.source.local.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.pinaki.desipe.di.InjectionConstants
import javax.inject.Inject
import javax.inject.Named

class SharedPreferencesManagerImpl @Inject constructor(
    @ApplicationContext context: Context,
    @Named(InjectionConstants.KEY_PREFERENCES_NAME) name: String?
) : SharedPreferencesManager {
    private var prefs: SharedPreferences? = null

    init {
        prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    override fun clearPrefs() {
        if (prefs != null) {
            prefs!!.edit().clear().apply()
        }
    }

    override fun containsKey(key: String?): Boolean? {
        return prefs!!.contains(key)
    }

    override fun getString(key: String?, defaultValue: String?): String? {
        return prefs!!.getString(key, defaultValue)
    }

    override fun getInteger(key: String?, defaultValue: Int): Int {
        return prefs!!.getInt(key, defaultValue)
    }

    override fun getLong(key: String?, defaultValue: Long): Long {
        return prefs!!.getLong(key, defaultValue)
    }

    override fun getFloat(key: String?, defaultValue: Float): Float {
        return prefs!!.getFloat(key, defaultValue)
    }

    override fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return prefs!!.getBoolean(key, defaultValue)
    }

    override fun putString(key: String?, value: String?) {
        prefs!!.edit().putString(key, value).apply()
    }

    override fun putInteger(key: String?, value: Int) {
        prefs!!.edit().putInt(key, value).apply()
    }

    override fun putFloat(key: String?, value: Float) {
        prefs!!.edit().putFloat(key, value).apply()
    }

    override fun putBoolean(key: String?, value: Boolean) {
        prefs!!.edit().putBoolean(key, value).apply()
    }

    override fun putLong(key: String?, value: Long) {
        prefs!!.edit().putLong(key, value).apply()
    }

    override fun remove(tag: String?) {
        prefs!!.edit().remove(tag).apply()
    }
}