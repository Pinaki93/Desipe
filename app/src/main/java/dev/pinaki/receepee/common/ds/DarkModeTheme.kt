package dev.pinaki.receepee.common.ds

enum class DarkThemeMode {
    LIGHT,
    DARK,
    AUTO,
    SYSTEM;

    companion object {

        @JvmStatic
        fun fromString(stringValue: String?): DarkThemeMode? {
            if (stringValue.isNullOrEmpty()) return null

            for (value in values()) {
                if (value.name.equals(stringValue, true)) {
                    return value
                }
            }

            return null
        }
    }
}