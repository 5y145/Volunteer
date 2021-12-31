package seongjun.volunteer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.repository.Repository

class DetailViewModel(): ViewModel() {

    val repository = Repository.getInstance()

    var volunteerDetailData: VolunteerDetailData? = null
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    fun getVolunteerDetail(programId: String) {
        viewModelScope.launch {
            isLoading.value = true
            volunteerDetailData = repository.getVolunteerDetail(programId)
            isLoading.value = false
        }
    }

    fun addBookMark(item: VolunteerDetailData, url: String) {
        viewModelScope.launch {
            repository.addBookMark(BookMarkData(item.programId, item.title, item.host, item.state, item.startDay, item.endDay, url))
        }
    }

    fun removeBookMark(programId: String) {
        viewModelScope.launch {
            repository.removeBookMark(programId)
        }
    }

    fun getNoticeDay(start: String, end: String): String {
        return if(end.toIntOrNull() == null) "${start.toInt() % 10000 / 100}/${start.toInt() % 100} ~ 마감시"
        else "${start.toInt() % 10000 / 100}/${start.toInt() % 100} ~ ${end.toInt() % 10000 / 100}/${end.toInt() % 100}"
    }

    fun getDay(start: Int, end: Int): String {
        return "${start % 10000 / 100}/${start % 100} ~ ${end % 10000 / 100}/${end % 100}"
    }

    fun getTime(start: Int, end: Int): String {
        return "$start ~ $end"
    }
}