package com.example.viewmodelactivity

import com.example.moviekotlin.RetroPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetDataService {

    @GET("/photos")
     fun getAllPhotos(): Call<List<RetroPhoto>>

    @GET("https://jsonplaceholder.typicode.com/photos/{id}")
     fun getSinglePhoto(@Path("id") pathId: Int): Call<RetroPhoto>


}

