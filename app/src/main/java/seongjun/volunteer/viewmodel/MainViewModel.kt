package seongjun.volunteer.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.repository.Repository

class MainViewModel: ViewModel() {

    val repository = Repository.getInstance()

    // Home Fragment
    val isComplete = MutableLiveData<Boolean>().apply { value = false }
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    var sidoCode = ""
    var gugunCode = ""
    var pageNumber = 1
    var isSearch = false
    var isEnd = false
    var searchText = ""
    var startDay = ""
    var endDay = ""

    // BookMark Fragment
    var isVolunteerExist = MutableLiveData<Boolean>().apply { value = false }
    var programId = ""
    var url = ""
    var isBookMark = false
    var fromBookMark = false

    // List
    var volunteerList = MutableLiveData<MutableList<VolunteerData>>().apply { value = ArrayList() }
    val bookMarkList = repository.getBookMarkDataList()

    // Use Retrofit
    fun getVolunteerList() {
        viewModelScope.launch {
            if (!isSearch) { // 기본 조회
                if (!isLoading.value!! && !isEnd) {
                    isLoading.value = true
                    val result = repository.getVolunteerList(sidoCode, gugunCode, pageNumber)
                    if (result.isEmpty()) {
                        isEnd = true
                    } else {
                        addList(result)
                        pageNumber++
                    }
                    isComplete.value = true
                    isLoading.value = false
                }
            } else { // 상세 조회
                if (!isLoading.value!! && !isEnd) {
                    isLoading.value = true
                    val result =
                        if(sidoCode == "" && gugunCode == "" && startDay == "" && endDay == "") { repository.getVolunteerListWithKeyword(searchText, pageNumber) }
                        else { repository.getVolunteerListWithOption(startDay, endDay, searchText, sidoCode, gugunCode,  pageNumber) }
                    if (result.isNotEmpty()) addList(result)
                    isEnd = true
                    isComplete.value = true
                    isLoading.value = false
                }
            }
        }
    }

    // Home Fragment
    fun clickSido(code: String) {
        isSearch = false
        sidoCode = code
        gugunCode = ""
        pageNumber = 1
        isEnd = false
        volunteerList.value!!.clear()
        getVolunteerList()
    }

    fun clickGugun(code: String) {
        isSearch = false
        gugunCode = code
        pageNumber = 1
        isEnd = false
        volunteerList.value!!.clear()
        getVolunteerList()
    }

    fun clickSearch(searchText: String, sidoCode: String, gugunCode: String, startDay: String, endDay: String) {
        isSearch = true
        this.searchText= searchText
        this.sidoCode = sidoCode
        this.gugunCode = gugunCode
        this.startDay = startDay
        this.endDay = endDay
        pageNumber = 1
        isEnd = false
        volunteerList.value!!.clear()
        getVolunteerList()
    }

    fun isVolunteerExist(programId: String, url: String, isBookMark: Boolean, fromBookMark: Boolean) {
        viewModelScope.launch {
            val volunteerDetailData = repository.getVolunteerDetail(programId)
            if (volunteerDetailData != null) {
                this@MainViewModel.programId = programId
                this@MainViewModel.url = url
                this@MainViewModel.isBookMark = isBookMark
                this@MainViewModel.fromBookMark = fromBookMark
                isVolunteerExist.value = true
            }
            else {
                if (isBookMark) repository.removeBookMark(programId)
                this@MainViewModel.programId = ""
                this@MainViewModel.url = ""
                this@MainViewModel.isBookMark = false
                this@MainViewModel.fromBookMark = false
            }
        }
    }

    private fun addList(newList: MutableList<VolunteerData>) {
        var oldList = volunteerList.value!!
        for (item in newList) { if (!oldList.any { it.programId == item.programId }) oldList.add(item) }
        volunteerList.value = oldList
    }
}