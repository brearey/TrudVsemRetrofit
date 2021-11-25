package ru.oktemsec.trudvsemretrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.oktemsec.trudvsemretrofit.interfaces.TrudVsemApi
import ru.oktemsec.trudvsemretrofit.models.SimpleResponse
import ru.oktemsec.trudvsemretrofit.models.TrudVsemResponse
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val editText: EditText = findViewById(R.id.editText)

        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://opendata.trudvsem.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val trudVsemApi: TrudVsemApi = retrofit.create(TrudVsemApi::class.java)
            val call: Call<TrudVsemResponse> = trudVsemApi.getVacancy(editText.text.toString())

            if (editText.text.toString() != "") {
                progressBar.visibility = View.VISIBLE

                call.enqueue(object : Callback<TrudVsemResponse> {
                    override fun onFailure(call: Call<TrudVsemResponse>, t: Throwable) {
                        Log.e("brearey", "Failed", t)
                    }
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<TrudVsemResponse>,
                        response: Response<TrudVsemResponse>
                    ) {
                        Log.d("brearey", "Response received: ${response.body()}")
                        if (response.isSuccessful) {
                            val result: TrudVsemResponse? = response.body()
                            textView.text = """
                                Результаты поиска в РС(Я):
                                Результатов: ${result?.meta?.total}
                                Первая компания в списке: ${result?.results?.vacancies?.get(0)?.vacancy?.company?.name}
                                Зарплата: ${result?.results?.vacancies?.get(0)?.vacancy?.salary} ${result?.results?.vacancies?.get(0)?.vacancy?.currency}
                            """.trimIndent()
                            Log.d("brearey", "Result: ${result?.status.toString()}")

                            progressBar.visibility = View.INVISIBLE
                        }
                        else {
                            Log.d("brearey", "Don't success: ${response.code()}")
                            try {
                                textView.text = response.errorBody().toString()
                                progressBar.visibility = View.INVISIBLE
                            } catch (e: IOException) {
                                Log.e("brearey", e.message.toString())
                            }
                        }
                    }
                })
            }
            else {
                editText.hint = "Введите запрос"
            }
        }
    }
}