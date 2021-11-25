package ru.oktemsec.trudvsemretrofit.models

import com.google.gson.annotations.SerializedName

data class Company(
    val companycode: String,
    val email: String,
    @SerializedName("hr-agency")
    val hr_agency: Boolean,
    val inn: String,
    val kpp: String,
    val name: String,
    val ogrn: String,
    val phone: String,
    val url: String
)