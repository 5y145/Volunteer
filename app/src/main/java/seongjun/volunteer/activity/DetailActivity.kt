package seongjun.volunteer.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import seongjun.volunteer.R
import seongjun.volunteer.databinding.ActivityDetailBinding
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.repository.Repository
import seongjun.volunteer.viewmodel.DetailViewModel
import seongjun.volunteer.viewmodel.MainViewModel

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
        viewModel.getVolunteerDetail(programId)
        setObserver()
    }

    private fun setView(item: VolunteerDetailData?) {
        if (item == null) return

        binding.tvProgramId.text = item.programId // 봉사 아이디
        binding.tvField.text = item.field // 봉사 분야
        binding.tvTitle.text = item.title // 봉사 제목

        binding.tvArea.text = item.area // 봉사 지역
        binding.tvPlace.text = item.place // 봉사 장소
        binding.tvNeedPersonNumber.text = "${item.needPersonNumber}명" // 모집 인원

        binding.tvIsFamilyPossible.visibility = if (item.isFamilyPossible == "Y") View.VISIBLE else View.GONE
        binding.tvIsGroupPossible.visibility = if (item.isGroupPossible == "Y") View.VISIBLE else View.GONE

        when(item.state) { // 모집 상태
            1 -> binding.tvState.text = "모집대기"
            2 -> binding.tvState.text = "모집중"
            3 -> binding.tvState.text = "모집완료"
        }

        binding.tvContents.text = item.contents // 봉사 내용

        binding.tvNoticeDay.text = getNoticeDay(item.noticeStartDay, item.noticeEndDay) // 모집 기간
        binding.tvDay.text = getDay(item.startDay, item.endDay) // 활동 기간
        binding.tvTime.text = getTime(item.startTime, item.endTime) // 활동 시간
        binding.tvIsAdultPossible.visibility = if (item.isAdultPossible == "Y") View.VISIBLE else View.GONE
        binding.tvIsYoungPossible.visibility = if (item.isYoungPossible == "Y") View.VISIBLE else View.GONE

        binding.tvManager.text = item.manager// 담당자
        binding.tvPhoneNumber.text = item.phoneNumber // 전화번호
        binding.tvEmail.text = if (item.email == "null" || item.email == "@") "" else item.email // 이메일

        binding.btnUrl.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        binding.ibBookMark.setOnClickListener {
            viewModel.bookMark(item, url)
        }

        binding.llContainer.visibility = View.VISIBLE
        binding.pb.visibility = View.GONE
    }

    private fun setObserver() {
        viewModel.isLoading.observe(this, {
            if (!viewModel.isLoading.value!! && viewModel.volunteerDetailData != null) setView(viewModel.volunteerDetailData)
        })

        viewModel.isBookMark.observe(this, {
            if (!viewModel.isBookMark.value!!) {
                binding.ibBookMark.setBackgroundResource(R.drawable.sharp_bookmark_border_24)
            } else {
                binding.ibBookMark.setBackgroundResource(R.drawable.sharp_bookmark_24)
            }
        })
    }

    private fun getNoticeDay(start: String, end: String): String {
        return if(end.toIntOrNull() == null) "${start.toInt() % 10000 / 100}/${start.toInt() % 100} ~ 마감시"
        else "${start.toInt() % 10000 / 100}/${start.toInt() % 100} ~ ${end.toInt() % 10000 / 100}/${end.toInt() % 100}"
    }

    private fun getDay(start: Int, end: Int): String {
        return "${start % 10000 / 100}/${start % 100} ~ ${end % 10000 / 100}/${end % 100}"
    }

    private fun getTime(start: Int, end: Int): String {
        return "$start ~ $end"
    }
}