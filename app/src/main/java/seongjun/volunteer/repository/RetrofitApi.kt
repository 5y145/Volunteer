package seongjun.volunteer.repository

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import seongjun.volunteer.model.MainData

interface RetrofitApi {
    @GET("volunteer_list")
    suspend fun selectAllMain(schSido: String, schSign1: String, pageNo: Int) : Response<List<MainData>>

    @POST("todo/{word}")
    suspend fun insertTodo(@Path("word") word: String) : Response<Unit>

    @DELETE("todo/{id}")
    suspend fun deleteTodo(@Path("id") id: Int) : Response<Unit>
}