package seongjun.volunteer.repository

import retrofit2.Response
import retrofit2.http.*
import seongjun.volunteer.model.*

interface Api {
    @GET("volunteer_list")
    suspend fun getVolunteerList(
        @Query("progrmBgnde")startDay: String,
        @Query("progrmEndde")endDay: String,
        @Query("schSido")sidoCode: String,
        @Query("schSign1")gugunCode: String,
        @Query("pageNo")pageNumber: Int
    ) : Response<MutableList<VolunteerData>>

    @GET("volunteer_list")
    suspend fun getVolunteerList(
        @Query("progrmBgnde")startDay: String,
        @Query("progrmEndde")endDay: String,
        @Query("schSido")sidoCode: String,
        @Query("pageNo")pageNumber: Int
    ) : Response<MutableList<VolunteerData>>

    @GET("volunteer_list")
    suspend fun getVolunteerListWithText(
        @Query("progrmBgnde")startDay: String,
        @Query("progrmEndde")endDay: String,
        @Query("keyword")searchText: String,
        @Query("schSido")sidoCode: String,
        @Query("schSign1")gugunCode: String,
        @Query("pageNo")pageNumber: Int
    ) : Response<MutableList<VolunteerData>>

    @GET("volunteer_list")
    suspend fun getVolunteerListWithText(
        @Query("progrmBgnde")startDay: String,
        @Query("progrmEndde")endDay: String,
        @Query("keyword")searchText: String,
        @Query("schSido")sidoCode: String,
        @Query("pageNo")pageNumber: Int
    ) : Response<MutableList<VolunteerData>>

    @GET("volunteer_item")
    suspend fun getVolunteerDetail(
        @Query("progrmRegistNo")programId: String
    ) : Response<VolunteerDetailData>
}