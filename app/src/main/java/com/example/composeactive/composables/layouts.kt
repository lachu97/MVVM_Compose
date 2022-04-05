package com.example.composeactive.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeactive.models.meals
import com.example.composeactive.navigation.Screen
import com.example.composeactive.viewmodels.netviewmodel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun verticalist(
    data: List<meals>,
    navController: NavController,
    viewmodel: netviewmodel
) {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        itemsIndexed(items = data) { index, item ->
            customcard(data = item, onclick = {
                navController.navigate(Screen.moredetail.route)
                viewmodel.selectitem(item)
                viewmodel.assignItem(item = item)
            })
        }
    }
}

@Composable
fun customcard(data: meals, onclick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable(onClick = onclick),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            data.image.let {
                Surface(elevation = 5.dp, border = BorderStroke(1.dp, color = Color.Black)) {
                    CoilImage(

                        imageModel = it,
                        modifier = Modifier
                            .width(160.dp)
                            .fillMaxHeight()
                            .padding(10.dp),
                        contentScale = ContentScale.Crop,
                        shimmerParams = ShimmerParams(
                            baseColor = MaterialTheme.colors.background,
                            highlightColor = Color.LightGray,
                            durationMillis = 350,
                            dropOff = 0.65f,
                            tilt = 20f
                        ),
                        // shows an error text message when request failed.
                        failure = {
                            Text(text = "image request failed.${it}")
                        }
                    )
                }
            }
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.Start
            ) {
                data.name.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                data.description.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}