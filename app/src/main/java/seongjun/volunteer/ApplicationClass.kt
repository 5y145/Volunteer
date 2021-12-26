package seongjun.volunteer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.repository.AppDataBase
import seongjun.volunteer.repository.Repository
import seongjun.volunteer.repository.RetrofitApi

class ApplicationClass: Application() {

    companion object{
//        var volunteerList: MutableList<VolunteerData> = ArrayList()
//        val bookMarkList: MutableList<BookMarkData> = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }
}