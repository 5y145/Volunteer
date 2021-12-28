package seongjun.volunteer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookMarkData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "program_id")
    val program_id: Int, // 프로그램 id
    @ColumnInfo(name = "program_title")
    val program_title: String, // 프로그램 제목
    @ColumnInfo(name = "program_state")
    val program_state: Int, // 모집 상태
    @ColumnInfo(name = "startDay")
    val startDay: Int, // 시작일
    @ColumnInfo(name = "endDay")
    val endDay: Int, // 종료일
    @ColumnInfo(name = "host")
    val host: String, // 기관
    @ColumnInfo(name = "url")
    val url: String, // 주소
)
