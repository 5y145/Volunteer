package seongjun.volunteer.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.model.VolunteerData

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
    suspend fun getVolunteerList(startDay: String, endDay: String, sidoCode: String, gugunCode: String, pageNumber: Int): MutableList<VolunteerData> {
        val response = RetrofitInstance.API.getVolunteerList(startDay, endDay, sidoCode, gugunCode, pageNumber)
        return if (response.isSuccessful) response.body() as MutableList<VolunteerData> else ArrayList()
    }

    suspend fun getVolunteerListWithText(startDay: String, endDay: String, searchText: String, sidoCode: String, gugunCode: String, pageNumber: Int): MutableList<VolunteerData> {
        val response = RetrofitInstance.API.getVolunteerListWithText(startDay, endDay, searchText, sidoCode, pageNumber)
        return if (response.isSuccessful) response.body() as MutableList<VolunteerData> else ArrayList()
    }

    suspend fun getVolunteerDetail(programId: String): VolunteerDetailData? {
        val response = RetrofitInstance.API.getVolunteerDetail(programId)
        return if (response.isSuccessful) response.body() as VolunteerDetailData else null
    }

    companion object {
        private var instance: Repository? = null
        fun getInstance(): Repository { return instance as Repository }
        fun getInstance(application : Application): Repository {
            if (instance == null) instance = Repository(application)
            return instance as Repository
        }
    }
}