package com.example.composeactive.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeactive.models.meals
import com.example.composeactive.navigation.Screen
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Detail(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


//        Text(
//            text = "Detail Screen",
//            modifier = Modifier.clickable {
//                navControllerv  .navigate(Screen.Home.route)
//            },
//            color = MaterialTheme.colors.primary
//        )
        Button(onClick = {  navController.navigate(Screen.Home.route) }) {
            Text(text = "CLick to Detail Screen")
            
        }
    }
}

@Composable
fun Moredetail(meals: meals) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        meals.image.let {
            CoilImage(
                imageModel = it,
                Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Crop,
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color.LightGray,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                )
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        meals.name.let {
            Text(
                text = it, modifier = Modifier.padding(5.dp),
                fontSize = 23.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        meals.description.let {
            Text(
                text = it, modifier = Modifier.padding(10.dp),
                fontSize = 27.sp, fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Normal
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row(modifier = Modifier.padding(5.dp)) {
            meals.price.let {
                Text(text = it.toString(), fontStyle = FontStyle.Normal, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            meals.category.let {
                Text(
                    text = it,
                    fontStyle = FontStyle.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }

    }
}