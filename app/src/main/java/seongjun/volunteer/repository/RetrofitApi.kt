package seongjun.volunteer.repository

import retrofit2.Response
import retrofit2.http.*
import seongjun.volunteer.model.*

interface RetrofitApi {

    // 봉사활동
    @GET("volunteer_list")
    suspend fun getVolunteerList(@Query("schSido")schSido: String, @Query("schSign1")schSign1: String, @Query("pageNo")pageNo: Int) : Response<List<MainData>>

    @GET("volunteer_list")
    suspend fun getVolunteerList(keyword: String, pageNo: Int) : Response<List<MainData>>

    @GET("volunteer_item")
    suspend fun getVolunteer(progrmRegistNo: Int) : Response<DetailData>

    // 유저
//    @GET("user")
//    suspend fun isUser(name: String) : Response<Boolean>
//
//    @POST("user")
//    suspend fun addUser(@Body name: String) : Response<UserData>
//
//    @PUT("user")
//    suspend fun setUser(@Body userData: UserData) : Response<UserData>

//    @GET("bookmark/{program_id}/{user_id}")
//    suspend fun isBookmark(@Path("program_id") program_id: Int, @Path("user_id") user_id: Int) : Response<Boolean>

//    @GET("bookmark/{user_id}")
//    suspend fun getBookmarkList(@Path("user_id") user_id: Int) : Response<List<MainData>>

//    // 리뷰
//    @GET("review")
//    suspend fun getReviewList() : Response<List<ReviewData>>
//
//    @POST("review")
//    suspend fun addReview(@Body reviewData: ReviewData) : Response<Unit>
//
//    @PUT("review")
//    suspend fun setReview(@Body reviewData: ReviewData) : Response<Unit>
//
//    @DELETE("review")
//    suspend fun removeReview(@Body reviewData: ReviewData) : Response<Unit>
//
//    // 댓글
//    @GET("comment/{review_id}")
//    suspend fun getCommentList(@Path("review_id") review_id: Int) : Response<List<CommentData>>
//
//    @POST("comment")
//    suspend fun addComment(@Body commentData: CommentData) : Response<Unit>
//
//    @PUT("comment")
//    suspend fun setComment(@Body commentData: CommentData) : Response<Unit>
//
//    @DELETE("comment")
//    suspend fun removeComment(@Body commentData: CommentData) : Response<Unit>
}