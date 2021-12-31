package seongjun.volunteer.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.repository.Repository

class MainViewModel: ViewModel() {

    val repository = Repository.getInstance()

    // Home Fragment
    val isComplete = MutableLiveData<Boolean>().apply { value = false }
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    var isSearching = false
    var sidoCode = ""
    var gugunCode = ""
    var pageNumber = 1
    var isEnd = false
    var searchText = ""
    var startDay = ""
    var endDay = ""

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
                val result = repository.getVolunteerList(searchText, sidoCode, gugunCode, startDay, endDay, pageNumber)
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

    fun clickSearch(searchText: String, sidoCode: String, gugunCode: String, startDay: String, endDay: String) {
        isSearching = true
        this.searchText= searchText
        this.sidoCode = sidoCode
        this.gugunCode = gugunCode
        this.startDay = startDay
        this.endDay = endDay
        pageNumber = 1
        isEnd = false
        volunteerList.value!!.clear()
        getVolunteerListWithSearch()
    }
}