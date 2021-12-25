package seongjun.volunteer.model

data class DetailData(
    val actBeginTm: Int, // 활동 시작시간
    val actEndTm: Int, // 활동 종료시간
    val actPlace: String, // 활동 장소
    val actWkdy: String, //
    val adultPosblAt: String, // 성인 가능여부
    val appTotal: Int, //
    val email: String, // 연락 이메일
    val familyPosblAt: String, // 가족 가능여부
    val fxnum: String, // 연락 팩스
    val grpPosblAt: String,  //
    val gugunCd: Int, // 구군 id
    val mnnstNm: String, // 장소 명
    val nanmmbyNm: String, // 지역 명
    val nanmmbyNmAdmn: String, // 책임자 명
    val noticeBgnde: Int, // 알림 시작일
    val noticeEndde: Int, // 알림 종료일
    val pbsvntPosblAt: String, //
    val postAdres: String, // 주소
    val progrmBgnde: Int, //
    val progrmCn: String, // 활동 세부정보
    val progrmEndde: Int, //
    val progrmRegistNo: Int, // 프로그램 id
    val progrmSj: String, // 프로그램 제목
    val progrmSttusSe: Int, //
    val rcritNmpr: Int, //
    val sidoCd: Int, // 시도 id
    val srvcClCode: String, // 봉사 분야
    val telno: String, // 연락 번호
    val yngbgsPosblAt: String //
)