package com.example.composeactive.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeactive.Greeting
import com.example.composeactive.models.category
import com.example.composeactive.models.meals
import com.example.composeactive.navigation.Screen
import com.example.composeactive.viewmodels.netviewmodel

@Composable
fun Navgraph(
    navcontrol: NavHostController,
    mymeals: List<meals>,
    mycategory: List<category>,
    vModel:netviewmodel
) {
    NavHost(
        navController = navcontrol,
        startDestination = Screen.detail.route
    ) {
        composable(route = Screen.Home.route) {
            Greeting("Android", mymeals, mycategory,navcontrol,vModel)
        }
        composable(route = Screen.detail.route) {
            Detail(navcontrol)
        }
        composable(route = Screen.moredetail.route) {
            vModel.getselecteditem?.let { it -> Moredetail(meals = it) }
        }
    }
}