package seongjun.volunteer.model

import com.google.gson.annotations.SerializedName

data class VolunteerData(
    @SerializedName("progrmRegistNo") // 봉사 아이디
    val programId: String,
    @SerializedName("srvcClCode") // 봉사 분야
    val field: String,
    @SerializedName("sidoCd") // 시도 코드
    val sidoCode: String,
    @SerializedName("gugunCd") // 구군 코드
    val gugunCode: String,
    @SerializedName("progrmSj") // 봉사 제목
    val title: String,
    @SerializedName("nanmmbyNm") // 모집 기관
    val host: String,
    @SerializedName("actPlace") // 봉사 장소
    val place: String,
    @SerializedName("progrmSttusSe") // 모집 상태
    val state: Int,
    @SerializedName("noticeBgnde") // 모집 시작일
    val noticeStartDay: String,
    @SerializedName("noticeEndde") // 모집 종료일
    val noticeEndDay: String,
    @SerializedName("progrmBgnde") // 봉사 시작일
    val startDay: Int,
    @SerializedName("progrmEndde") // 봉사 종료일
    val endDay: Int,
    @SerializedName("actBeginTm") // 봉사 시작시간
    val startTime: Int,
    @SerializedName("actEndTm") // 봉사 종료시간
    val endTime: Int,
    @SerializedName("adultPosblAt") // 성인 가능여부 [Y, N]
    val isAdultPossible: String = "N",
    @SerializedName("yngbgsPosblAt") // 청소년 가능여부 [Y, N]
    val isYoungPossible: String = "N",
    @SerializedName("url") // 신청 url
    val url: String
)