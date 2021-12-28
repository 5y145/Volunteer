package seongjun.volunteer.model

import java.io.Serializable

data class VolunteerData(
    val actBeginTm: Int,
    val actEndTm: Int,
    val actPlace: String,
    val adultPosblAt: String,
    val gugunCd: String, // 구군 id
    val nanmmbyNm: String, // 기관
    val noticeBgnde: String,
    val noticeEndde: String,
    val progrmBgnde: String, // 시작일
    val progrmEndde: String, // 종료일
    val progrmRegistNo: Int, // 프로그램 id
    val progrmSj: String, // 프로그램 제목
    val progrmSttusSe: Int, // 모집 상태
    val sidoCd: String, // 시도 id
    val srvcClCode: String,
    val url: String, // 참여하기
    val yngbgsPosblAt: String
): Serializable