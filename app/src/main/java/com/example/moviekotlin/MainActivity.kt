package com.example.moviekotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelactivity.GetDataService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.IDN
import java.nio.file.Path

class MainActivity : AppCompatActivity() {

    internal lateinit var progressDialog: ProgressDialog
    private var textView: TextView? = null
    private var imageView: ImageView? = null
    private var picasso: Picasso? = null

    private lateinit var photo: RetroPhoto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photo = RetroPhoto()
        setContentView(R.layout.main_activity)
        textView = findViewById<TextView>(R.id.text_child)
        imageView = findViewById<ImageView>(R.id.image_url)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading")
        progressDialog.show()

        picasso = Picasso.with(this)

        val service = RetrofitClientInstance.getRetrofit()
        val call = service.getSinglePhoto(taskId)
        call.enqueue(object : Callback<RetroPhoto> {
            override fun onResponse(call: Call<RetroPhoto>, response: Response<RetroPhoto>) {
                progressDialog.dismiss()
                generateSinglePhotoList(response.body()!!)
            }

            override fun onFailure(call: Call<RetroPhoto>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity, "something wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun generateSinglePhotoList(photo: RetroPhoto) {
        textView!!.setText(photo.getTitle())
        Picasso.with(this).load(photo.getThumbnailUrl())
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}





