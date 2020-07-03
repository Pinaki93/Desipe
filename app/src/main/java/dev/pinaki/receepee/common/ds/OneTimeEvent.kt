package dev.pinaki.receepee.common.ds

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 *
 * This is based on the approach of treating events as a part of the state
 * and this approach prevents a consumed event from getting consumed again
 *
 * More can be read about this here
 * [https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150]
 *
 * Courtesy: Jose Alcérreca
 * [medium: https://medium.com/@JoseAlcerreca]
 * [github: https://github.com/JoseAlcerreca]
 *
 * This class was taken from the following gist:
 * [https://gist.githubusercontent.com/JoseAlcerreca/5b661f1800e1e654f07cc54fe87441af/raw/d1d9ad561c16f4d04367424ac5f5b305ba691852/Event.kt]
 *
 */
open class OneTimeEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
