package seongjun.volunteer.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import seongjun.volunteer.model.BookMarkData

@Database(entities = [BookMarkData::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        private const val DB_NAME = "db_name"
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase { // singleton pattern
            if (instance == null) {
                synchronized(this){
                    instance = Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME).build()
                }
            }
            return instance!!
        }
    }
}