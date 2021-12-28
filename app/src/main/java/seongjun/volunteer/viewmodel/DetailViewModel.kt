package seongjun.volunteer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.repository.Repository

class DetailViewModel: ViewModel() {

    val repository = Repository.getInstance()

    var volunteerDetailData: VolunteerDetailData? = null
    private val bookMarkList: LiveData<List<BookMarkData>> = repository.getBookMarkDataList()

    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    fun getVolunteerDetail(programId: String) {
        viewModelScope.launch {
            isLoading.value = true
            volunteerDetailData = repository.getVolunteerDetail(programId)
            isLoading.value = false
        }
    }

    fun addBookMark(item: VolunteerDetailData) = viewModelScope.launch {
        repository.addBookMark(BookMarkData(0, item.programId, item.title, item.area, item.place, item.startDay, item.endDay))
    }

    fun removeBookMark(programId: String) = viewModelScope.launch {
        repository.removeBookMark(programId)
    }

    fun isBookMark(programId: String): Boolean {
        return bookMarkList.value!!.any { it.programId == programId }
    }
}