package com.example.composeactive.models

class generic<T>(var list: List<T>?, var message:String?){

    fun genlist():List<T>{
        return list!!
    }

    fun genmessage():String{
        return message!!
    }
}