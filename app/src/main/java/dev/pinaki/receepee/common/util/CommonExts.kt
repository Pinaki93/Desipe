package dev.pinaki.receepee.common.util

import android.app.Activity
import android.content.res.Resources
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the collection.
 */
inline fun <T> Iterable<T>.sumByFloat(selector: (T) -> Float): Float {
    var sum: Float = 0f
    for (element in this) {
        sum += selector(element)
    }
    return sum
}


/**
 * Shows a toast at the center of the screen
 */
fun Activity.toast(message: Int) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

/**
 * Shows a toast at the center of the screen
 */
fun Fragment.toast(message: Int) {
    requireActivity().toast(message)
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isGone() = visibility == View.GONE

fun LottieAnimationView.startLoopingAnimation(@RawRes animation: Int) {
    setAnimation(animation)
    repeatCount = LottieDrawable.INFINITE
    playAnimation()
}

val Float.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

val Float.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density)