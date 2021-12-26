package seongjun.volunteer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.repository.Repository

class HomeViewModel: ViewModel() {

    val repository = Repository.getInstance()

    var volunteerList: ArrayList<VolunteerData> = ArrayList()
    val bookMarkList: LiveData<List<BookMarkData>> = repository.getBookMarkList()

    var isComplete: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val isSearching: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val isEnd: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val isDetailSpinner: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    val pageNum: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 1 }
    val sidoCode: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val gugunCode: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val searchText: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    init {
        getVolunteerList()
//        Log.d("@@@", "HomeViewModel init ${volunteerList.value!!.size}")
    }

    fun getVolunteerList() {
        if (!isLoading.value!! && !isSearching.value!! && !isEnd.value!!) { // 지역별로 조회
            viewModelScope.launch {
                isLoading.value = true
                volunteerList.addAll(repository.getVolunteerList(sidoCode.value!!, gugunCode.value!!, pageNum.value!!))
                isLoading.value = false
                isComplete.value = true
            }
        } else {isEnd.value = true}
    }
}