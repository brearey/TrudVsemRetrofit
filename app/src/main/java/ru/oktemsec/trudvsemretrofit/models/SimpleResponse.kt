package ru.oktemsec.trudvsemretrofit.models

data class SimpleResponse(
    val status:Int,
    val request:String,
    val meta:String,
    val results:String
)
