package seongjun.volunteer.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import seongjun.volunteer.model.BookMarkData

@Dao
interface Dao {
    @Query("SELECT * FROM bookmark ORDER BY programId DESC")
    fun getBookMarkList(): LiveData<List<BookMarkData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookMark(bookMarkData: BookMarkData)

    @Query("DELETE FROM bookmark WHERE programId = :programId")
    suspend fun removeBookMark(programId: String)
}