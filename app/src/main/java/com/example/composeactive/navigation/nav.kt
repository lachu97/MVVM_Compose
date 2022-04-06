package com.example.composeactive.navigation

sealed class Screen(val route:String){
    object Home:Screen(route = "home")
    object detail:Screen(route = "detail")
    object moredetail:Screen(route = "moredetail")
    object SomeMoreDeail:Screen(route = "SomeDetail")
    object Extradetail:Screen(route = "Extradetail")
}