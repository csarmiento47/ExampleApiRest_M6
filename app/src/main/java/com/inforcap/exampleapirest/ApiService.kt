package com.inforcap.exampleapirest

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    fun getAllPost(): Call<ArrayList<PostEntity>>

}