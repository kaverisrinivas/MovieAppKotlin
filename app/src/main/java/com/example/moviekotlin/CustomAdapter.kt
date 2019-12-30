package com.example.moviekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList


class CustomAdapter(private var context: Context) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    private val photoList = ArrayList<RetroPhoto>(0)
    private var picasso: Picasso? = null


    fun CustomAdapter(
        context: Context,
        picasso: Picasso,
        onNoteClickListener: OnNoteClickListener
    ) {
        this.picasso = picasso
        this.context = context
        this.onNoteClickListener = onNoteClickListener
    }

    fun setList(photoList: List<RetroPhoto>) {
        this.photoList.clear()
        this.photoList.addAll(photoList)
        notifyDataSetChanged()
    }

    class CustomViewHolder(private var mView: View) : RecyclerView.ViewHolder(mView) {
        var imageView: ImageView = mView.findViewById(R.id.image_view)
        var txtTitle: TextView = mView.findViewById(R.id.text_view)

    }

    private var onNoteClickListener: OnNoteClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_scrolling, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.txtTitle.setText(photoList[position].getTitle())
        Picasso.with(context).load(photoList[position].getThumbnailUrl())
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView)
        holder.txtTitle.setOnClickListener { onNoteClickListener?.onNoteClick(position) }


    }

    override fun getItemCount(): Int {

        return photoList.size
    }
}
