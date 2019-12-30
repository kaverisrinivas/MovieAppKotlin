package com.example.moviekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollingActivity : AppCompatActivity(),OnNoteClickListener {
    private var adapter:CustomAdapter?=null
    private var recyclerView:RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_scrolling)
        recyclerView= findViewById(R.id.recycler_view)
        adapter=CustomAdapter(this)
        recyclerView!!.setHasFixedSize(true)
        recyclerView?.adapter=adapter
        recyclerView?.layoutManager=LinearLayoutManager(this)
        val model = ViewModelProviders.of(this).get(MyViewModel::class.java)
        model.loadUsers()
        model.getUsers().observe(this,
            Observer<List<RetroPhoto>> { retroPhotos ->
                adapter!!.setList(retroPhotos)
            })

    }

    override fun onNoteClick(position: Int) {
        Toast.makeText(this, "hello busy people", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}



