package com.example.moviekotlin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitClientInstance {

    @GET("/photos")

    fun getAllPhotos(): Call<List<RetroPhoto>>

    @GET("https://jsonplaceholder.typicode.com/photos/{id}")
    fun getSinglePhoto(@Path("id") pathId: Int): Call<RetroPhoto>

    companion object {

        private val BASE_URL: String?
            get() = "https://jsonplaceholder.typicode.com"
        
        fun getRetrofit(): RetrofitClientInstance {
            var retrofit: Retrofit? = null
            if (retrofit == null)
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit?.create(RetrofitClientInstance::class.java)!!
        }
    }

}
