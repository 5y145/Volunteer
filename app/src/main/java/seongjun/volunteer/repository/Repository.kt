package seongjun.volunteer.repository

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.model.VolunteerData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Repository(application : Application) {

    // Room Dao
    private val dao = DataBase.getInstance(application)!!.getDao()

    // Use Room
    fun getBookMarkDataList(): LiveData<List<BookMarkData>> {
        return dao.getBookMarkList()
    }

    suspend fun addBookMark(bookMarkData: BookMarkData) {
        return dao.addBookMark(bookMarkData)
    }

    suspend fun removeBookMark(programId: String) {
        return dao.removeBookMark(programId)
    }

    // Use Retrofit
    suspend fun getVolunteerList(sidoCode: String, gugunCode: String, pageNumber: Int): MutableList<VolunteerData> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nextDay = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val response = RetrofitInstance.API.getVolunteerList(sidoCode, gugunCode, nextDay, nextDay, pageNumber)
            if (response.isSuccessful) response.body() as MutableList<VolunteerData> else ArrayList()
        } else {
            val response = RetrofitInstance.API.getVolunteerList(sidoCode, gugunCode, pageNumber)
            if (response.isSuccessful) response.body() as MutableList<VolunteerData> else ArrayList()
        }
    }

    suspend fun getVolunteerList(searchText: String, sidoCode: String, gugunCode: String, startDay: String, endDay: String, pageNumber: Int): MutableList<VolunteerData> {
        Log.d("@@@", "검색 요청보냄 '${searchText}' '${sidoCode}' '${gugunCode}' ${startDay} ${endDay} ${pageNumber}")
        val response = RetrofitInstance.API.getVolunteerList(searchText, sidoCode, gugunCode, startDay, endDay, pageNumber)
        return if (response.isSuccessful) response.body() as MutableList<VolunteerData> else ArrayList()
    }

    suspend fun getVolunteerDetail(programId: String): VolunteerDetailData? {
        val response = RetrofitInstance.API.getVolunteerDetail(programId)
        return if (response.isSuccessful) response.body() as VolunteerDetailData else null
    }

    companion object {
        private var instance: Repository? = null
        fun getInstance(application : Application): Repository {
            if (instance == null) instance = Repository(application)
            return instance as Repository
        }
    }
}