package com.fabianospdev.mindflow.core.routes

open class ScreenRoute(val route: String) {
    object Splash : ScreenRoute("splash")
    object Home : ScreenRoute("home")
    object Settings : ScreenRoute("settings")
    object Login : ScreenRoute("login")
}
