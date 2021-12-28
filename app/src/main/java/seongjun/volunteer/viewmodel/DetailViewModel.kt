package seongjun.volunteer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import seongjun.volunteer.model.BookMarkData
import seongjun.volunteer.model.DetailData
import seongjun.volunteer.repository.Repository

class DetailViewModel: ViewModel() {

    val repository = Repository.getInstance()

    var volunteer: MutableLiveData<DetailData?> = MutableLiveData<DetailData?>().apply { value = null }
    val bookMarkList: LiveData<List<BookMarkData>> = Repository.getInstance().getBookMarkList()

    val isComplete: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }

    fun loadVolunteer(program_id: Int) {
        viewModelScope.launch {

            volunteer.value = repository.getVolunteer(program_id)
            isComplete.value = true
        }
    }

    fun addBookMark(bookMarkData: BookMarkData) = viewModelScope.launch {
        repository.addBookMark(bookMarkData)
    }

    fun removeBookMark(program_id: Int) = viewModelScope.launch {
        repository.removeBookMark(program_id)
    }

    fun isBookMark(program_id: Int): Boolean {
        return bookMarkList.value!!.any { it.program_id == program_id }
    }
}