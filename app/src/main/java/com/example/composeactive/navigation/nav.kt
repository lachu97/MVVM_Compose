package com.example.composeactive.navigation

sealed class Screen(val route:String){
    object Home:Screen(route = "home")
    object detail:Screen(route = "detail")
    object moredetail:Screen(route = "moredetail")
}