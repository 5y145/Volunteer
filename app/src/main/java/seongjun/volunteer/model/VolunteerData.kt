package seongjun.volunteer.model

data class VolunteerData(
    val actBeginTm: Int,
    val actEndTm: Int,
    val actPlace: String,
    val adultPosblAt: String,
    val gugunCd: Int, // 구군 id
    val nanmmbyNm: String, // 기관
    val noticeBgnde: Int,
    val noticeEndde: Int,
    val progrmBgnde: Int, // 시작일
    val progrmEndde: Int, // 종료일
    val progrmRegistNo: Int, // 프로그램 id
    val progrmSj: String, // 프로그램 제목
    val progrmSttusSe: Int, // 모집 상태
    val sidoCd: Int, // 시도 id
    val srvcClCode: String,
    val url: String, // 참여하기
    val yngbgsPosblAt: String
)