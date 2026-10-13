package com.putra.techexpress

object AppRoutes {
    const val Login = "login"
    const val Katalog = "katalog"
    const val Profil = "profil"
    const val Detail = "detail/{gadgetId}"
    const val Diskon = "diskon"

    fun detail(gadgetId: Int): String {
        return "detail/$gadgetId"
    }
}