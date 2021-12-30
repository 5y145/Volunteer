package seongjun.volunteer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import seongjun.volunteer.ApplicationClass
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.repository.Repository

class DetailViewModel(): ViewModel() {

    val repository = ApplicationClass.repository

    var volunteerDetailData: VolunteerDetailData? = null
    val bookMarkList = repository.getBookMarkDataList()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isBookMark = MutableLiveData<Boolean>().apply { value = false }

    fun getVolunteerDetail(programId: String) {
        viewModelScope.launch {
            isLoading.value = true
            volunteerDetailData = repository.getVolunteerDetail(programId)
            isBookMark(programId)
            isLoading.value = false
        }
    }

    fun bookMark(item: VolunteerDetailData, url: String) {
        viewModelScope.launch {
            if (isBookMark.value!!) {
                repository.removeBookMark(item.programId)
            } else {
                repository.addBookMark(BookMarkData(item.programId, item.title, item.host, item.state, item.startDay, item.endDay, url))
            }
        }
    }

    private suspend fun isBookMark(programId: String) {
        if (bookMarkList.value == null) {
            Log.d("@@@", "isBookMark: 널입니다.")
        } else {
            isBookMark.postValue(false)
            for (item in bookMarkList.value!!) {
                if (item.programId == programId) {
                    isBookMark.postValue(true)
                }
            }
        }
    }
}