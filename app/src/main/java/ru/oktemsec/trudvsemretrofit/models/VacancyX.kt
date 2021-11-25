package ru.oktemsec.trudvsemretrofit.models

import com.google.gson.annotations.SerializedName

data class VacancyX(
    val addresses: Addresses,
    val category: Category,
    val company: Company,
    @SerializedName("creation-date")
    val creation_date: String,
    val currency: String,
    val duty: String,
    val employment: String,
    val id: String,
    @SerializedName("job-name")
    val job_name: String,
    val region: Region,
    val requirement: Requirement,
    val salary: String,
    val salary_max: Int,
    val salary_min: Int,
    val schedule: String,
    val source: String,
    val term: Term,
    val vac_url: String
)