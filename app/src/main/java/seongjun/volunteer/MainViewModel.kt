package seongjun.volunteer

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.DetailData
import seongjun.volunteer.model.MainData
import seongjun.volunteer.repository.Repository

class MainViewModel: ViewModel() {

    val repository = Repository.getInstance()

    var pageNumber = 1
    var mainList: MutableLiveData<List<MainData>> = MutableLiveData(ArrayList())
    val bookMarkList: LiveData<List<BookMarkData>> = repository.getBookMarkList()
    var volunteerDetailData: MutableLiveData<DetailData?> = MutableLiveData(null)


    init {
        viewModelScope.launch {
            mainList.value = repository.getVolunteerList()
        }

    }

    // Use Room
    fun addBookMark(bookMarkData: BookMarkData) = viewModelScope.launch {
        repository.addBookMark(bookMarkData)
    }

    fun removeBookMark(bookMarkData: BookMarkData) = viewModelScope.launch {
        repository.removeBookMark(bookMarkData)
    }

//    // Use Retrofit
//    fun getVolunteerList(sido: String, gugun: String) {
//        viewModelScope.launch {
//            mainList.value = repository.getVolunteerList(sido, gugun, pageNumber)
//            pageNumber++
//        }
//    }
//
//    fun getVolunteerList(keyword: String) {
//        viewModelScope.launch {
//            mainList.value = repository.getVolunteerList(keyword, pageNumber)
//            pageNumber++
//        }
//    }
//
//    fun getVolunteer(progrmRegistNo: Int) {
//        viewModelScope.launch {
//            volunteerDetailData.value = null
//            volunteerDetailData.value = repository.getVolunteer(progrmRegistNo)
//        }
//    }
}