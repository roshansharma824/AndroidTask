package com.example.androidtask.data

import com.example.androidtask.pojo.PostItem
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getDataList(): List<PostItem>
}