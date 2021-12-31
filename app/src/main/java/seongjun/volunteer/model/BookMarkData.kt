package seongjun.volunteer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookMarkData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "programId") // 봉사 아이디
    val programId: String,

    @ColumnInfo(name = "title") // 봉사 제목
    val title: String,

    @ColumnInfo(name = "host")  // 모집 기관
    val host: String,

    @ColumnInfo(name = "state") // 모집 상태
    val state: Int,

    @ColumnInfo(name = "startDay") // 봉사 시작일
    val startDay: Int,

    @ColumnInfo(name = "endDay") // 봉사 종료일
    val endDay: Int,

    @ColumnInfo(name = "url") // 신청 url
    val url: String
)
