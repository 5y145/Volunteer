package seongjun.volunteer.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.DetailData
import seongjun.volunteer.model.VolunteerData

class Repository(context: Context) {

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
    suspend fun getTest(pageNum: Int) = RetrofitInstance.api.getTest("", "", pageNum).await()

    // Use Retrofit
    suspend fun getVolunteerList(sido: String, gugun: String, pageNum: Int): ArrayList<VolunteerData> {
        val response = RetrofitInstance.api.getVolunteerList(sido, gugun, pageNum)
        return if (response.isSuccessful) response.body() as ArrayList<VolunteerData> else ArrayList()
    }

    suspend fun getVolunteerList(keyword: String, pageNum: Int): ArrayList<VolunteerData> {
        val response = RetrofitInstance.api.getVolunteerList(keyword, pageNum)
        return if (response.isSuccessful) response.body() as ArrayList<VolunteerData> else ArrayList()
    }

    suspend fun getVolunteer(program_id: Int): DetailData {
        val response = RetrofitInstance.api.getVolunteer(program_id)
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