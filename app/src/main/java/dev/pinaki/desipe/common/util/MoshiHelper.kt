package dev.pinaki.desipe.common.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

/**
 * Base class to create json adapters for Moshi.
 */
open class MoshiHelper {

    val moshi: Moshi = Moshi.Builder().build()

    protected inline fun <reified T> adapter(): JsonAdapter<T> = moshi.adapter(T::class.java)

    protected inline fun <reified T> listAdapter(): JsonAdapter<List<T>> {
        val listMyData: Type = Types.newParameterizedType(
            MutableList::class.java,
            T::class.java
        )
        return moshi.adapter(listMyData)
    }
}