package seongjun.volunteer.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import seongjun.volunteer.model.BookMarkData

@Dao
interface Dao {
    @Query("SELECT * FROM bookmark ORDER BY id DESC")
    fun getBookMarkList(): LiveData<List<BookMarkData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookMark(bookMarkData: BookMarkData)

    @Delete
    suspend fun removeBookMark(bookMarkData: BookMarkData)
}