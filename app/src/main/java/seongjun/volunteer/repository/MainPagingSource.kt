package seongjun.volunteer.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import seongjun.volunteer.model.MainData

private const val PAGE_INDEX = 1

/*
simpleApi : 데이터를 제공하는 인스턴스
userId : 쿼리를 위한 값
 */

class MainPagingSource(private val retrofitInstance : RetrofitInstance, private val index : Int): PagingSource<Int, MainData>() {
    override fun getRefreshKey(state: PagingState<Int, MainData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MainData> {
        // LoadParams : 로드할 키와 항목 수 , LoadResult : 로드 작업의 결과
        return try {
            // 키 값이 없을 경우 기본값을 사용함
            val position = params.key ?: PAGE_INDEX

            // 데이터를 제공하는 인스턴스의 메소드 사용
            val response = retrofitInstance.getCustomPost2(
                userId = index,
                sort = "id",
                order = "asc"
            )
            val post = response?.body()

            /* 로드에 성공 시 LoadResult.Page 반환
            data : 전송되는 데이터
            prevKey : 이전 값 (위 스크롤 방향)
            nextKey : 다음 값 (아래 스크롤 방향)
             */
            LoadResult.Page(
                data = post!!,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = null
            )

            // 로드에 실패 시 LoadResult.Error 반환
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}