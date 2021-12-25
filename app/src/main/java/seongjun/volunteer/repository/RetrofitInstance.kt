package seongjun.volunteer.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val SERVER_URL = "http://seongjunjang.cafe24app.com/volunteer/"
    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : RetrofitApi by lazy { retrofit.create(RetrofitApi::class.java) }
}