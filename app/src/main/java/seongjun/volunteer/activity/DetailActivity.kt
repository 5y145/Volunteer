package seongjun.volunteer.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import seongjun.volunteer.R
import seongjun.volunteer.databinding.ActivityDetailBinding
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var programId: String
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        programId = intent.getStringExtra("programId").toString()
        url = intent.getStringExtra("url").toString()

        binding.container.visibility = View.GONE
        binding.pb.visibility = View.VISIBLE

        viewModel.loadVolunteer(volunteerData.id)
        setObserver()
    }

    private fun setView() {
        val data = viewModel.volunteer.value!!

        binding.tvProgramId.text = "${data.progrmRegistNo}" // 프로그램 번호
        binding.tvField.text = "${data.srvcClCode}" // 봉사 분야
        binding.tvProgramName.text = "${data.progrmSj}" // 봉사 제목

        binding.tvPlace.text = "${data.actPlace}" // 봉사 장소
        binding.tvHost.text = "${data.nanmmbyNm}" // 모집 기관
        binding.tvTotalNeed.text = "${data.appTotal}/${data.rcritNmpr}" // 모집 인원

        binding.tvFamily.visibility = if (data.familyPosblAt == "Y") View.VISIBLE else View.GONE
        binding.tvGroup.visibility = if (data.grpPosblAt == "Y") View.VISIBLE else View.GONE

        when(data.progrmSttusSe) { // 봉사 상태
            1 -> binding.tvState.text = "모집대기"
            2 -> binding.tvState.text = "모집중"
            3 -> binding.tvState.text = "모집완료"
        }

        binding.tvProgramContents.text = "${data.progrmCn}" // 봉사 내용

        binding.tvNotice.text = getNotice(data.noticeBgnde, data.noticeEndde) // 모집 기간
        binding.tvDate.text = getDate(data.progrmBgnde, data.progrmEndde) // 활동 기간
        binding.tvTime.text = getTime(data.actBeginTm, data.actEndTm) // 활동 시간
        binding.tvIsPossible1.text = if (data.adultPosblAt == "Y") "성인 가능" else "" // 성인 가능 여부
        binding.tvIsPossible2.text = if (data.yngbgsPosblAt == "Y") "청소년 가능" else "" // 청소년 가능 여부

        binding.tvNanmmbyNmAdmn.text = "${data.nanmmbyNmAdmn}" // 담당자
        binding.tvTelno.text = "${data.telno}" // 전화번호
        binding.tvEmail.text = if (data.email == null || data.email == "null" || data.email == "@") "" else "${data.email}" // 이메일

        binding.btnGo.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(volunteerData.url)))
        }

        binding.ibBookMark.setOnClickListener {
            if (!viewModel.isBookMark(volunteerData.id)) {
                viewModel.addBookMark(
                    BookMarkData(0,
                        volunteerData.id,
                        volunteerData.progrmSj,
                        volunteerData.progrmSttusSe,
                        volunteerData.progrmBgnde.toInt(),
                        volunteerData.progrmEndde.toInt(),
                        volunteerData.nanmmbyNm,
                        volunteerData.url)
                )
                binding.ibBookMark.setBackgroundColor(applicationContext.resources.getColor(R.color.orange))
            } else {
                viewModel.removeBookMark(volunteerData.id)
                binding.ibBookMark.setBackgroundColor(applicationContext.resources.getColor(R.color.gray7))
            }
        }

        binding.container.visibility = View.VISIBLE
        binding.pb.visibility = View.GONE
    }

    private fun setObserver() {
        viewModel.isComplete.observe(this, {
            if (viewModel.isComplete.value!! && viewModel.volunteer.value != null) {
                viewModel.isComplete.value = false
                setView()
            }
        })

        viewModel.bookMarkList.observe(this, {
            Log.d("@@@", "bookMarkList: ${viewModel.bookMarkList.value!!.size}")
        })
    }

    private fun getNotice(start: String, end: String): String {
        return if(end.toIntOrNull() == null) "${start.toInt() % 10000 / 100}/${start.toInt() % 100} ~ 마감시"
        else "${start.toInt() % 10000 / 100}/${start.toInt() % 100} ~ ${end.toInt() % 10000 / 100}/${end.toInt() % 100}"
    }

    private fun getDate(start: Int, end: Int): String {
        return "${start % 10000 / 100}/${start % 100} ~ ${end % 10000 / 100}/${end % 100}"
    }

    private fun getTime(start: Int, end: Int): String {
        return "$start ~ $end"
    }
}