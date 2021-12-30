package seongjun.volunteer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import seongjun.volunteer.ApplicationClass
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData

class DetailViewModel(): ViewModel() {

    val repository = ApplicationClass.repository

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
}