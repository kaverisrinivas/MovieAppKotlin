package com.example.moviekotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodelactivity.GetDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    private val users: MutableLiveData<List<RetroPhoto>> by lazy {
        MutableLiveData<List<RetroPhoto>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<RetroPhoto>> {
        return users
    }

    fun loadUsers() {
        val service = RetrofitClientInstance.getRetrofit()
        val call = service.getAllPhotos()
        call.enqueue(object : Callback<List<RetroPhoto>> {
            override fun onResponse(
                call: Call<List<RetroPhoto>>,
                response: Response<List<RetroPhoto>>
            ) {
                users.postValue(response.body())
            }

            override fun onFailure(call: Call<List<RetroPhoto>>, t: Throwable) {

            }
        })
    }
}