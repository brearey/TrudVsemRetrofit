package ru.oktemsec.trudvsemretrofit.interfaces

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.oktemsec.trudvsemretrofit.models.TrudVsemResponse

interface TrudVsemApi {
    @GET("v1/vacancies/region/14?offset=1&limit=1")
    fun getVacancy(@Query("text") text:String = "java"): Call<TrudVsemResponse>
}