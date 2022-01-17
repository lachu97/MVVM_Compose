package com.example.composeactive

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeactive.composables.*
import com.example.composeactive.models.NetworkResult
import com.example.composeactive.models.category
import com.example.composeactive.models.meals
import com.example.composeactive.ui.theme.ComposeActiveTheme
import com.example.composeactive.viewmodels.netviewmodel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val viewModel: netviewmodel by viewModels()
    lateinit var navcontrol: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeActiveTheme {
                val mymeals = viewModel.newfoods.value
                val mycategory = viewModel.mycategory.value
                navcontrol = rememberNavController()
//                newshimmer()
                Log.i("MainActivity","New Values in the value =${viewModel.genericlass.genlist().forEach { 
                    Log.i("Inside","${it}")
                }}")
                Surface(color = MaterialTheme.colors.background) {
                    Navgraph(
                        navcontrol = navcontrol,
                        mymeals = mymeals,
                        mycategory = mycategory,
                        vModel = viewModel
                    )
                }
            }

        }
           viewModel.responseData.observe(this,{
               when (it) {
                   is NetworkResult.Loading -> {
                       Log.i("Network Logs", "Loading")
                   }
                   is NetworkResult.Success -> {
                       Log.i(
                           "Network Logs",
                           "Success and value = ${it.data?.name} and ID= ${it.data?.id}"
                       )

//                            Surface(color = MaterialTheme.colors.background) {
//                                Navgraph(
//                                    navcontrol = navcontrol,
//                                    mymeals = mymeals,
//                                    mycategory = mycategory,
//                                    vModel = viewModel
//                                )
//                            }
                   }
                   is NetworkResult.Error -> {
                       Log.i("Network Logs", "Error in Network Call =${it.message}")
                   }
               }
           })

        // A surface container using the 'background' color from the theme

    }
}

@Composable
fun voidnm() {
    Text(text = "Error", modifier = Modifier.background(Color.DarkGray))
}

@Composable
fun Greeting(
    name: String,
    mydata: List<meals>,
    catdat: List<category>,
    navController: NavController,
    viewmodel: netviewmodel
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = name)
            Spacer(modifier = Modifier.padding(5.dp))
            Hlist(mydata = catdat, navController, viewmodel)
            Spacer(modifier = Modifier.padding(5.dp))
            verticalist(data = mydata, navController, viewmodel)
            //Spacer(modifier = Modifier.padding(5.dp))
            // ClickableSample()
        }

        Column(modifier = Modifier.fillMaxSize()) {
            repeat(7) {
                AnimatedShimmer(rload = viewmodel.isload.value)
            }
        }
        circularloader(isloading = viewmodel.isload.value)
    }

}

//@Composable
//fun newshimmer() {
//    Column {
//        repeat(8) {
//            AnimatedShimmer()
//        }
//    }
//}
@Composable
fun ClickableSample() {
    val count = remember { mutableStateOf(0) }
    // content that you want to make clickable
    voidnm()
    Text(
        text = count.value.toString(),
        modifier = Modifier.clickable { count.value += 1 }
    )
}

