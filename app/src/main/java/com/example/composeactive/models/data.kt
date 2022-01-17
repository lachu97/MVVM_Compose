package com.example.composeactive.models

data class data(val name:String,val id:Int)
data class meals(
    val f_id:Int,
    val name:String,
    val description:String,
    val image:String,
    val type:String,
    val price:Int,
    val category:String,
    val cat_id:Int,
    val rating:Int,
    val origin:String
)
data class category(
    val cat_id: Int,
    val category_name:String,
    val cat_image:String
)