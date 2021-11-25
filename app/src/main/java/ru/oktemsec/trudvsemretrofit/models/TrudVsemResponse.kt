package ru.oktemsec.trudvsemretrofit.models

data class TrudVsemResponse(
    val meta: Meta,
    val request: Request,
    val results: Results,
    val status: String
)