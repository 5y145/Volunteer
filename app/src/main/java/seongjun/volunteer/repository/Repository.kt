package seongjun.volunteer.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import seongjun.volunteer.ApplicationClass
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.DetailData
import seongjun.volunteer.model.MainData

class Repository(context: Context) {

    val ss = "zxczxczxc"

    // Room Dao
    private val dao = AppDataBase.getInstance(context).getDao()

    // Use Room
    fun getBookMarkList(): LiveData<List<BookMarkData>> {
        return dao.getBookMarkList()
    }

    suspend fun addBookMark(bookMarkData: BookMarkData) {
        dao.addBookMark(bookMarkData)
    }

    suspend fun removeBookMark(bookMarkData: BookMarkData) {
        dao.removeBookMark(bookMarkData)
    }

    // Use Retrofit
    suspend fun getVolunteerList(): List<MainData> {
        val response = RetrofitInstance.api.getVolunteerList("", "", 1)
        return if (response.isSuccessful) response.body() as List<MainData> else ArrayList()
    }

//    suspend fun getVolunteerList(keyword: String, pageNo: Int): List<MainData> {
//        val response = RetrofitInstance.api.getVolunteerList(keyword, pageNo)
//        return if (response.isSuccessful) response.body() as List<MainData> else ArrayList()
//    }

    suspend fun getVolunteer(progrmRegistNo: Int): DetailData {
        val response = RetrofitInstance.api.getVolunteer(progrmRegistNo)
        return response.body() as DetailData
    }

    companion object {
        private var instance: Repository? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = Repository(context)
                Log.d("@@@", "initialize: $instance")
            }
        }

        fun getInstance(): Repository {
            return instance?: throw IllegalStateException("Repository Not Initialized")
        }
    }
}