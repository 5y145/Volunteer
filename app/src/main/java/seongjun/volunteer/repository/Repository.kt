package seongjun.volunteer.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

class Repository {

    fun getPost(index : Int) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 1000,
                enablePlaceholders = false
            ),
            // 사용할 메소드 선언
            pagingSourceFactory = { MainPagingSource(RetrofitInstance.api, index)}
        ).liveData
}