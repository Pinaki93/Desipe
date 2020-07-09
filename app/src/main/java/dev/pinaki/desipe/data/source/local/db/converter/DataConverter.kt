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

package dev.pinaki.desipe.data.source.local.db.converter

import androidx.room.TypeConverter
import dev.pinaki.desipe.data.model.Ingredient
import dev.pinaki.desipe.helper.moshi.DesipeMoshiHelper

class DataConverter {

    @TypeConverter
    fun convertStringListToJson(list: List<String>): String {
        return DesipeMoshiHelper.stringListAdapter.toJson(list)
    }

    @TypeConverter
    fun convertJsonToStringList(json: String): List<String> {
        return DesipeMoshiHelper.stringListAdapter.fromJson(json)!!
    }

    @TypeConverter
    fun convertIngredientListToJson(list: List<Ingredient>): String {
        return DesipeMoshiHelper.ingredientsListAdapter.toJson(list)
    }

    @TypeConverter
    fun convertJsonToIngredientList(json: String): List<Ingredient> {
        return DesipeMoshiHelper.ingredientsListAdapter.fromJson(json)!!
    }
}