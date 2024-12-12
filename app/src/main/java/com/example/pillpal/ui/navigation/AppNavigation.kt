package com.example.pillpal.ui.navigation

enum class Screen {
    HOME,
    DETAIL,
    SPLASH,
    ADD_PILL
}

sealed class NavigationItem(val route: String) {
    object Home: NavigationItem(Screen.HOME.name)
    object Detail: NavigationItem(Screen.DETAIL.name)
    object Splash: NavigationItem(Screen.SPLASH.name)
    object AddPill: NavigationItem(Screen.ADD_PILL.name)
}