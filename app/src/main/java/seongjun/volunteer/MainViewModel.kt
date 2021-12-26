package seongjun.volunteer

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.DetailData
import seongjun.volunteer.model.VolunteerData
import seongjun.volunteer.repository.Repository

class MainViewModel: ViewModel() {

    val repository = Repository.getInstance()

    var volunteerList: MutableLiveData<MutableList<VolunteerData>> = MutableLiveData(ArrayList())
    val bookMarkList: LiveData<List<BookMarkData>> = repository.getBookMarkList()
    var volunteerDetailData: MutableLiveData<DetailData?> = MutableLiveData(null)

    // Use Room
    fun addBookMark(bookMarkData: BookMarkData) = viewModelScope.launch {
        repository.addBookMark(bookMarkData)
    }

    fun removeBookMark(bookMarkData: BookMarkData) = viewModelScope.launch {
        repository.removeBookMark(bookMarkData)
    }

    // Use Retrofit
    fun getVolunteerList(sido: String, gugun: String, pageNum: Int) {
        viewModelScope.launch {
            val result: MutableList<VolunteerData> = ArrayList()
            val old = volunteerList.value
            val new = repository.getVolunteerList(sido, gugun, pageNum)
            result.addAll(old!!)
            result.addAll(new)
            volunteerList.value = result
        }
    }

    fun getVolunteerList(keyword: String, pageNum: Int) {
        viewModelScope.launch {
            val result = repository.getVolunteerList(keyword, pageNum)
            for (data in result) {
                volunteerList.value!!.add(data)
            }
        }
    }

    fun getVolunteer(program_id: Int) {
        viewModelScope.launch {
            volunteerDetailData.value = null
            volunteerDetailData.value = repository.getVolunteer(program_id)
        }
    }
}