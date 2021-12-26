package seongjun.volunteer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.repository.Repository

class HomeViewModel: ViewModel() {

    val repository = Repository.getInstance()

    var volunteerList: MutableLiveData<MutableList<VolunteerData>> = MutableLiveData<MutableList<VolunteerData>>().apply { value = ArrayList() }

    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val isComplete: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val sidoCode: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val gugunCode: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    var page = 1
    var isEnd = false
    var isSearching = false
    var searchText = ""

    init {
        Log.d("###", "뷰모델: 새로생성")
        loadVolunteerList() }

    fun loadVolunteerList() {
        viewModelScope.launch {
            if (!isLoading.value!! && !isEnd) {
                isLoading.value = true

                if (page == 1) volunteerList.value!!.clear()

                if(!isSearching) { // 지역별로 조회
                    val result = repository.getVolunteerList(sidoCode.value!!, gugunCode.value!!, page)
                    if(result.size == 0) { isEnd = true }
                    else { volunteerList.value!!.addAll(result) }
                }

                if(isSearching) { // 검색어로 조회
                    val result = repository.getVolunteerList(searchText, page)
                    if(result.size == 0) { isEnd = true }
                    else { volunteerList.value!!.addAll(result) }
                }

                page++
                isComplete.value = true
                isLoading.value = false
            }
        }
    }
}