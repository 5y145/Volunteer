package seongjun.volunteer.model

object LocationInfo {
    val sidoName = arrayOf("전체", "서울", "부산", "대구", "인천", "광주", "대전", "울산",
        "세종", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도")

    val sidoCode = arrayOf(
        "", "6110000", "6260000", "6270000", "6280000", "6290000", "6300000", "6310000",
        "5690000", "6410000", "6420000", "6430000", "6440000", "6450000", "6460000", "6470000", "6480000", "6500000")

    val seoulName = arrayOf("전체", "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구",
        "도봉구", "노원구", "은평구", "서대문구", "마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구",
        "서초구", "강남구", "송파구", "강동구")

    val seoulCode = arrayOf("", "3000000", "3010000", "3020000", "3030000", "3040000", "3050000", "3060000", "3070000", "3080000",
        "3090000", "3100000", "3110000", "3120000", "3130000", "3140000", "3150000", "3160000", "3170000", "3180000", "3190000", "3200000",
        "3210000", "3220000", "3230000", "3240000")

    val gyeonggiName = arrayOf(
        "전체", "수원시", "성남시", "고양시", "용인시", "부천시", "안산시", "안양시", "남양주시", "화성시", "평택시",
        "의정부시", "시흥시", "파주시", "광명시", "김포시", "군포시", "광주시", "이천시", "양주시", "오산시", "구리시",
        "안성시", "포천시", "의왕시", "하남시", "여주시", "양평군", "동두천시", "과천시", "가평군", "연천군")

    val gyeonggiCode = arrayOf(
        "", "3740000", "3780000", "3940000", "4050000", "3860000", "3930000", "3830000", "3990000", "5530000", "3910000",
        "3820000", "4010000", "4060000", "3900000", "4090000", "4020000", "5540000", "4070000", "5590000", "4000000", "3980000",
        "4080000", "5600000", "4030000", "4040000", "5700000", "4170000", "3920000", "3970000", "4160000", "4140000")

    val gyeonggi: Map<String, String> = mapOf(
        "전체" to "",
        "수원시" to "3740000",
        "성남시" to "3780000",
        "고양시" to "3940000",
        "용인시" to "4050000",
        "부천시" to "3860000",
        "안산시" to "3930000",
        "안양시" to "3830000",
        "남양주시" to "3990000",
        "화성시" to "5530000",
        "평택시" to "3910000",
        "의정부시" to "3820000",
        "시흥시" to "4010000",
        "파주시" to "4060000",
        "광명시" to "3900000",
        "김포시" to "4090000",
        "군포시" to "4020000",
        "광주시" to "5540000",
        "이천시" to "4070000",
        "양주시" to "5590000",
        "오산시" to "4000000",
        "구리시" to "3980000",
        "안성시" to "4080000",
        "포천시" to "5600000",
        "의왕시" to "4030000",
        "하남시" to "4040000",
        "여주시" to "5700000",
        "양평군" to "4170000",
        "동두천시" to "3920000",
        "과천시" to "3970000",
        "가평군" to "4160000",
        "연천군" to "4140000",
    )

    val seoul: Map<String, String> = mapOf(
        "전체" to "",
        "종로구" to "3000000",
        "중구" to "3010000",
        "용산구" to "3020000",
        "성동구" to "3030000",
        "광진구" to "3040000",
        "동대문구" to "3050000",
        "중랑구" to "3060000",
        "성북구" to "3070000",
        "강북구" to "3080000",
        "도봉구" to "3090000",
        "노원구" to "3100000",
        "은평구" to "3110000",
        "서대문구" to "3120000",
        "마포구" to "3130000",
        "양천구" to "3140000",
        "강서구" to "3150000",
        "구로구" to "3160000",
        "금천구" to "3170000",
        "영등포구" to "3180000",
        "동작구" to "3190000",
        "관악구" to "3200000",
        "서초구" to "3210000",
        "강남구" to "3220000",
        "송파구" to "3230000",
        "강동구" to "3240000",
    )

    val sido: Map<String, String> = mapOf(
        "전체" to "",
        "서울특별시" to "6110000",
        "부산광역시" to "6260000",
        "대구광역시" to "6270000",
        "인천광역시" to "6280000",
        "광주광역시" to "6290000",
        "대전광역시" to "6300000",
        "울산광역시" to "6310000",
        "세종특별자치시" to "5690000",
        "경기도" to "6410000",
        "강원도" to "6420000",
        "충청북도" to "6430000",
        "충청남도" to "6440000",
        "전라북도" to "6450000",
        "전라남도" to "6460000",
        "경상북도" to "6470000",
        "경상남도" to "6480000",
        "제주특별자치도" to "6500000",
    )

    val busan: Map<String, String> = mapOf(
        "전체" to "",
        "중구" to "3250000",
        "서구" to "3260000",
        "동구" to "3270000",
        "영도구" to "3280000",
        "부산진구" to "3290000",
        "동래구" to "3300000",
        "남구" to "3310000",
        "북구" to "3320000",
        "해운대구" to "3330000",
        "사하구" to "3340000",
        "금정구" to "3350000",
        "강서구" to "3360000",
        "연제구" to "3370000",
        "수영구" to "3380000",
        "사상구" to "3390000",
        "기장군" to "3400000",
    )


}