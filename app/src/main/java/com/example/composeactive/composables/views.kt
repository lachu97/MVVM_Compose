package com.example.composeactive.composables


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeactive.models.category
import com.example.composeactive.viewmodels.netviewmodel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Hlist(
    mydata: List<category>,
    navController: NavController,
    viewmodel: netviewmodel
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .height(184.dp)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        itemsIndexed(items = mydata) { index, item ->
            Chips(categorydata = item, onclick = {
                viewmodel.getitembyid(id = item.cat_id)
            })
        }
    }
}

@Composable
fun Chips(categorydata: category, onclick: () -> Unit) {
    Surface(
        elevation = 8.dp, modifier = Modifier
            .padding(5.dp)
            .width(120.dp), shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
                .clickable(onClick = onclick),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            categorydata.cat_image.let {
                CoilImage(
                    imageModel = it, modifier = Modifier
                        .padding(4.dp)
                        .height(105.dp)
                        .width(115.dp), shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.LightGray,
                        durationMillis = 350,
                        dropOff = 0.65f,
                        tilt = 20f
                    )
                )
            }
            Text(
                text = categorydata.category_name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}