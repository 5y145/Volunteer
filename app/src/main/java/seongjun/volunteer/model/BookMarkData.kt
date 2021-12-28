package seongjun.volunteer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookMarkData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "programId") // 봉사 아이디
    val programId: String,

    @ColumnInfo(name = "title") // 봉사 제목
    val title: String,

    @ColumnInfo(name = "area") // 지역 명
    val area: String,

    @ColumnInfo(name = "place") // 봉사 장소
    val place: String,

    @ColumnInfo(name = "startDay") // 봉사 시작일
    val startDay: Int,

    @ColumnInfo(name = "endDay") // 봉사 종료일
    val endDay: Int,
)
