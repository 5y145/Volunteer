package seongjun.volunteer.model

object AreaData {
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

    val busanName = arrayOf(
        "전체", "중구", "서구", "동구", "영도구",
        "부산진구", "동래구", "남구", "북구", "해운대구",
        "사하구", "금정구", "강서구", "연제구", "수영구",
        "사상구", "기장군"
    )

    val busanCode = arrayOf(
        "", "3250000", "3260000", "3270000", "3280000",
        "3290000", "3300000", "3310000", "3320000", "3330000",
        "3340000", "3350000", "3360000", "3370000", "3380000",
        "3390000", "3400000"
    )

    val daeguName = arrayOf(
        "전체", "중구", "동구", "서구", "남구",
        "북구", "수성구", "달서구", "달성군"
    )

    val daeguCode = arrayOf(
        "", "3410000", "3420000", "3430000", "3440000",
        "3450000", "3460000", "3470000", "3480000"
    )

    val incheonName = arrayOf(
        "전체", "중구", "동구", "미추홀구", "연수구",
        "남동구", "부평구", "계양구", "서구", "강화군",
        "옹진군"
    )

    val incheonCode = arrayOf(
        "", "3490000", "3500000", "3510000", "3520000",
        "3530000", "3540000", "3550000", "3560000", "3570000",
        "3580000"
    )

    val gwangjuName = arrayOf(
        "전체", "동구", "서구", "남구", "북구",
        "광산구"
    )

    val gwangjuCode = arrayOf(
        "", "3590000", "3600000", "3610000", "3620000",
        "3630000"
    )

    val daejeonName = arrayOf(
        "전체", "동구", "중구", "서구", "유성구",
        "대덕구"
    )

    val daejeonCode = arrayOf(
        "", "3640000", "3650000", "3660000", "3670000",
        "3680000"
    )

    val ulsanName = arrayOf(
        "전체", "중구", "남구", "동구", "북구",
        "울주군"
    )

    val ulsanCode = arrayOf(
        "", "3690000", "3700000", "3710000", "3720000",
        "3730000"
    )

    val gangwondoName = arrayOf(
        "전체", "춘천시", "원주시", "강릉시", "동해시",
        "태백시", "속초시", "삼척시", "홍천군", "횡성군",
        "영월군", "평창군", "정선군", "철원군", "화천군",
        "양구군", "인제군", "고성군", "양양군"
    )

    val gangwondoCode = arrayOf(
        "", "4180000", "4190000", "4200000", "4210000",
        "4220000", "4230000", "4240000", "4250000", "4260000",
        "4270000", "4280000", "4290000", "4300000", "4310000",
        "4320000", "4330000", "4340000", "4350000"
    )

    val chungcheongbukdoName = arrayOf(
        "전체", "청주시", "충주시", "제천시", "보은군",
        "옥천군", "영동군", "증평군", "진천군", "괴산군",
        "음성군", "단양군"
    )

    val chungcheongbukdoCode = arrayOf(
        "", "5710000", "4390000", "4400000", "4420000",
        "4430000", "4440000", "5570000", "4450000", "4460000",
        "4470000", "4480000"
    )

    val chungcheongnamdoName = arrayOf(
        "전체", "천안시", "공주시", "보령시", "아산시",
        "서산시", "논산시", "계룡시", "연기군", "당진시",
        "금산군", "부여군", "서천군", "청양군", "홍성군",
        "예산군", "태안군"
    )

    val chungcheongnamdoCode = arrayOf(
        "", "4490000", "4500000", "4510000", "4520000",
        "4530000", "4540000", "5580000", "4560000", "4630000",
        "4550000", "4570000", "4580000", "4590000", "4600000",
        "4610000", "4620000"
    )

    val jeonlabugdoName = arrayOf(
        "전체", "전주시", "군산시", "익산시", "정읍시",
        "남원시", "김제시", "완주군", "진안군", "무주군",
        "장수군", "임실군", "순창군", "고창군", "부안군"
    )

    val jeonlabugdoCode = arrayOf(
        "", "4640000", "4670000", "4680000", "4690000",
        "4700000", "4710000", "4720000", "4730000", "4740000",
        "4750000", "4760000", "4770000", "4780000", "4790000"
    )

    val jeonlanamdoName = arrayOf(
        "전체", "목포시", "여수시", "순천시", "나주시",
        "광양시", "담양군", "곡성군", "구례군", "고흥군",
        "보성군", "화순군", "장흥군", "강진군", "해남군",
        "영암군", "무안군", "함평군", "영광군", "장성군",
        "완도군", "진도군", "신안군"
    )

    val jeonlanamdoCode = arrayOf(
        "", "4800000", "4810000", "4820000", "4830000",
        "4840000", "4850000", "4860000", "4870000", "4880000",
        "4890000", "4900000", "4910000", "4920000", "4930000",
        "4940000", "4950000", "4960000", "4970000", "4980000",
        "4990000", "5000000", "5010000"
    )

    val gyeongsangbugdoName = arrayOf(
        "전체", "포항시", "경주시", "김천시", "안동시",
        "구미시", "영주시", "영천시", "상주시", "문경시",
        "경산시", "군위군", "의성군", "청송군", "영양군",
        "영덕군", "청도군", "고령군", "성주군", "칠곡군",
        "예천군", "봉화군", "울진군", "울릉군"
    )

    val gyeongsangbugdoCode = arrayOf(
        "", "5020000", "5050000", "5060000", "5070000",
        "5080000", "5090000", "5100000", "5110000", "5120000",
        "5130000", "5140000", "5150000", "5160000", "5170000",
        "5180000", "5190000", "5200000", "5210000", "5220000",
        "5230000", "5240000", "5250000", "5260000"
    )

    val gyeongsangnamdoName = arrayOf(
        "전체", "창원시", "진주시", "통영시", "사천시",
        "김해시", "밀양시", "거제시", "양산시", "의령군",
        "함안군", "창녕군", "고성군", "남해군", "하동군",
        "산청군", "함양군", "거창군", "합천군"
    )

    val gyeongsangnamdoCode = arrayOf(
        "", "5670000", "5310000", "5330000", "5340000",
        "5350000", "5360000", "5370000", "5380000", "5390000",
        "5400000", "5410000", "5420000", "5430000", "5440000",
        "5450000", "5460000", "5470000", "5480000"
    )
}