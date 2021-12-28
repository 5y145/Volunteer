package seongjun.volunteer.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.model.VolunteerData

class Repository(context: Context) {

    // Room Dao
    private val dao = DataBase.getInstance(context).getDao()

    // Use Room
    fun getBookMarkDataList(): LiveData<List<BookMarkData>> = dao.getBookMarkList()
    suspend fun addBookMark(bookMarkData: BookMarkData) = dao.addBookMark(bookMarkData)
    suspend fun removeBookMark(programId: String) = dao.removeBookMark(programId)

    // Use Retrofit
    suspend fun getVolunteerList(sidoCode: String, gugunCode: String, pageNumber: Int): List<VolunteerData> {
        val response = RetrofitInstance.API.getVolunteerList(sidoCode, gugunCode, pageNumber)
        return if (response.isSuccessful) response.body() as List<VolunteerData> else ArrayList()
    }

    suspend fun getVolunteerList(keyWord: String, pageNumber: Int): List<VolunteerData> {
        val response = RetrofitInstance.API.getVolunteerList(keyWord, pageNumber)
        return if (response.isSuccessful) response.body() as List<VolunteerData> else ArrayList()
    }

    suspend fun getVolunteerDetail(programId: String): VolunteerDetailData? {
        val response = RetrofitInstance.API.getVolunteerDetail(programId)
        return if (response.isSuccessful) response.body() as VolunteerDetailData else null
    }

    companion object {
        private var instance: Repository? = null
        fun initialize(context: Context) { if (instance == null) { instance = Repository(context) } }
        fun getInstance(): Repository { return instance?: throw IllegalStateException("Repository Not Initialized") }
    }
}