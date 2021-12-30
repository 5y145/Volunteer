package seongjun.volunteer.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import seongjun.volunteer.ApplicationClass
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerDetailData
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.repository.Repository

class MainViewModel: ViewModel() {

    val repository = ApplicationClass.repository

    // Home Fragment
    val isComplete = MutableLiveData<Boolean>().apply { value = false }
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    var isSearching = false
    var sidoCode = ""
    var gugunCode = ""
    var pageNumber = 1
    var isEnd = false
    var searchText = ""

    // List
    var volunteerList = MutableLiveData<MutableList<VolunteerData>>().apply { value = ArrayList() }
    val bookMarkList = repository.getBookMarkDataList()

    init {
        getVolunteerListWithArea()
    }

    // Use Retrofit
    fun getVolunteerListWithArea() {
        viewModelScope.launch {
            if (!isLoading.value!! && !isEnd) {
                isLoading.value = true
                val result = repository.getVolunteerList(sidoCode, gugunCode, pageNumber)
                if (result.isEmpty()) {
                    isEnd = true
                }
                else {
                    volunteerList.value!!.addAll(result)
                    pageNumber++
                }
                isComplete.postValue(true)
                isLoading.value = false
            }
        }
    }

    fun getVolunteerListWithSearch() {
        viewModelScope.launch {
            if (!isLoading.value!! && !isEnd) {
                isLoading.value = true
                val result = repository.getVolunteerList(searchText, pageNumber)
                if (result.isEmpty()) isEnd = true
                else {
                    volunteerList.value!!.addAll(result)
                    pageNumber++
                }
                isComplete.postValue(true)
                isLoading.value = false
            }
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

    // Home Fragment
    fun clickSido(code: String) {
        isSearching = false
        sidoCode = code
        gugunCode = ""
        pageNumber = 1
        isEnd = false
        volunteerList.value!!.clear()
        getVolunteerListWithArea()
    }

    fun clickGugun(code: String) {
        isSearching = false
        gugunCode = code
        pageNumber = 1
        isEnd = false
        volunteerList.value!!.clear()
        getVolunteerListWithArea()
    }

    fun clickSearch(text: String) {
        isSearching = true
        sidoCode = ""
        gugunCode = ""
        pageNumber = 1
        isEnd = false
        searchText= text
        volunteerList.value!!.clear()
        getVolunteerListWithSearch()
    }
}