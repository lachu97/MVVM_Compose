package com.example.composeactive.viewmodels

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeactive.models.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class netviewmodel : ViewModel() {
    //    private val _food= MutableLiveData<List<meals>>()
//    val foodstate: LiveData<List<meals>> get() = _food
    val newfoods: MutableState<List<meals>> = mutableStateOf(listOf())
    val isload: MutableState<Boolean> = mutableStateOf(false)

    var genericlass = generic(listOf<meals>(), "")

    // val itembyid: MutableState<List<meals>> = mutableStateOf(listOf())
    val mycategory: MutableState<List<category>> = mutableStateOf(listOf())
    var _selecteditem: MutableState<meals?> = mutableStateOf(null)
    val getselecteditem: meals? get() = _selecteditem.value
    val responseData: MutableLiveData<NetworkResult<data>> =
        MutableLiveData(NetworkResult.Loading())
    //New one for testing
    var _myselecteditem: MutableState<Any?> = mutableStateOf(null)
    val getSelectedItem get() = _myselecteditem.value
    val url = "http://blacky.tech/mealsapi/meals.php"
    val url2 = "http://blacky.tech/mealsapi/category.php"
    val url3 = "http://blacky.tech/mealsapi/search.php"
    val Client = HttpClient {
        install(JsonFeature) {
            serializer = GsonSerializer()
            accept(ContentType.Any)

        }
    }
    fun <T> assignItem(item: T) {
        _myselecteditem.value = item
        Log.i("ViewModel","Value in _MyselectedItem=${getSelectedItem}")
    }

    fun getItem(): Any? {
        return getSelectedItem
    }

    fun selectitem(meals: meals) {
        _selecteditem.value = meals
    }

    fun returnitem(): meals? {
        return getselecteditem
    }

    fun getitembyid(id: Int) {
        viewModelScope.launch {
            try {
                val items: ArrayList<meals> = Client.get(url3) {
                    parameter("catid", id)
                }
                newfoods.value = items

            } catch (e: NoTransformationFoundException) {
                Log.i("MainActivity", "Value ${e.message}")
            }
        }
    }

    suspend fun <T> getdatausignsealed(): NetworkResult<Any> {
        return try {
            val meals: ArrayList<meals> = Client.get(url)
            NetworkResult.Success(
                meals
            )
        } catch (e: NoTransformationFoundException) {
            NetworkResult.Error(
                null,
                e.message
            )

        } catch (er: NetworkErrorException) {
            NetworkResult.Error(
                null,
                er.message
            )
        }
    }

    init {
        viewModelScope.launch {
            try {
                isload.value = true
                responseData.value = NetworkResult.Success(null)
                delay(5000L)
                val meals: ArrayList<meals> = Client.get(url)
                val category: ArrayList<category> = Client.get(url2)
                val item=getdatausignsealed<Any>()
                item.let {
                    when(it)
                    {
                        is NetworkResult.Success ->{
                            val dataval=it.data as List<*>
                            dataval.let {
                                it.forEach {
                                    Log.i("MainActiviyt","Print ${it}")
                                }
                            }
                            Log.i("MainActiviyt","Value=${it.data}")
                        }
                        is NetworkResult.Error ->{

                        }
                        is NetworkResult.Loading ->{

                        }
                    }
                }
                newfoods.value = meals
                mycategory.value = category
                genericlass.list = meals
                genericlass.message = "Result OK"
                responseData.value = NetworkResult.Success(data = data("Success", 0))
                isload.value = false
            } catch (e: NoTransformationFoundException) {
                responseData.value = NetworkResult.Error(null, e.message)
                Log.i("MainActivity", "Value ${e.message}")
            } catch (err: NetworkErrorException) {
                responseData.value = NetworkResult.Error(null, err.message)
                Log.i("mainactibviy", "Error in Netswork ${err.message}")
            }
        }
    }

}