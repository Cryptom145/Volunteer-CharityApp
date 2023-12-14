package com.tarona.finalproject.network

import com.tarona.finalproject.models.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("/tweet/taronaFinal")
    fun getPostList(): Call<List<Post>>

    @GET("/tweet/taronaFinalEvent")
    fun getParticipantList(): Call<List<Post>>

    @GET("/tweet/taronaFinal/{id}")
    fun getPostById(@Path("id") id: String): Call<Post>

    @GET("/tweet/taronaFinalEvent/{id}")
    fun getParticipantListById(@Path("id") id: String): Call<Post>

    @POST("/tweet/taronaFinal")
    fun createPost(@Body post: Post): Call<Post>

    @POST("/tweet/taronaFinalEvent")
    fun enterEvent(@Body post: Post): Call<Post>

    @PUT("/tweet/taronaFinal/{id}")
    fun updatePost(@Path("id") id: String, @Body post: Post): Call<Post>

    @DELETE("/tweet/taronaFinal/{id}")
    fun deletePost(@Path("id") id: String): Call<Post>
}