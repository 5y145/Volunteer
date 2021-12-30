package seongjun.volunteer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookMarkData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "programId")
    val programId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "host")
    val host: String,
    @ColumnInfo(name = "state")
    val state: Int,
    @ColumnInfo(name = "startDay")
    val startDay: Int,
    @ColumnInfo(name = "endDay")
    val endDay: Int,
    @ColumnInfo(name = "url")
    val url: String
)
