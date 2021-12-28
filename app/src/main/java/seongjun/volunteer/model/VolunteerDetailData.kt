package seongjun.volunteer.model

import com.google.gson.annotations.SerializedName

data class VolunteerDetailData(
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

    @SerializedName("progrmCn") // 봉사 내용
    val contents: String,

    @SerializedName("mnnstNm") // 모집 기관 // xx복지센터
    val host: String,

    @SerializedName("nanmmbyNm") // 지역 명 // xx시 xx구
    val area: String,

    @SerializedName("actPlace") // 봉사 장소
    val place: String,

    @SerializedName("rcritNmpr") // 모집 인원
    val needPersonNumber: String,

    @SerializedName("appTotal") // 신청 인원 수 // 실시간 검색이 안되 제공 안함
    val nowPersonNumber: String,

    @SerializedName("progrmSttusSe") // 모집 상태 [1 : 모집대기, 2 : 모집중, 3 : 모집완료]
    val state: Int,

    @SerializedName("actWkdy") // 활동 요일 [1111100(월,화,수,목,금,토,일)]
    val week: String,



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



    @SerializedName("nanmmbyNmAdmn") // 담당자 명
    val manager: String,

    @SerializedName("postAdres") // 담당자 주소
    val managerAddress: String,

    @SerializedName("telno") // 연락처
    val phoneNumber: String,

    @SerializedName("email") // 이메일
    val email: String,

    @SerializedName("fxnum") // 팩스
    val fax: String,



    @SerializedName("adultPosblAt") // 성인 가능여부 [Y, N]
    val isAdultPossible: String,

    @SerializedName("yngbgsPosblAt") // 청소년 가능여부 [Y, N]
    val isYoungPossible: String,

    @SerializedName("familyPosblAt") // 가족 가능여부 [Y, N]
    val isFamilyPossible: String,

    @SerializedName("grpPosblAt") // 단체 가능여부 [Y, N]
    val isGroupPossible: String,

    @SerializedName("pbsvntPosblAt") // 설명 없음 [Y, N]
    val isUnknownPossible: String,
)