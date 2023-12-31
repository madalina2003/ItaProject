package com.tus.tusparking

sealed class Screen(val route: String){
    object HomeScreen: Screen("HomeScreen")
    object SignUpScreen: Screen("SignScreen")
    object Search: Screen("Search")
    object Maps: Screen("maps")
    object PaymentScreen:Screen("PaymentScreen")

    object PaymentSuccessScreen:Screen("PaymentSuccessScreen")
    object MapsScreen:Screen("Maps")
}
