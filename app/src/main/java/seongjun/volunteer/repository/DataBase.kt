package seongjun.volunteer.repository

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import seongjun.volunteer.model.BookMarkData

@Database(entities = [BookMarkData::class], version = 5, exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        private const val DB_NAME = "volunteer_db"
        private var instance: DataBase? = null

        fun getInstance(application : Application): DataBase? { // singleton pattern
            if (instance == null) {
                synchronized(this){
                    instance = Room.databaseBuilder(application, DataBase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }
    }
}