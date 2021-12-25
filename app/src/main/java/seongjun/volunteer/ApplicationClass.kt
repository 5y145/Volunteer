package seongjun.volunteer

import android.app.Application
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import seongjun.volunteer.repository.AppDataBase
import seongjun.volunteer.repository.Repository
import seongjun.volunteer.repository.RetrofitApi

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }
}