package com.innocent.crop2cashassessment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.innocent.crop2cashassessment.R

class ExhibitImagesAdapter (var imageList: List<String>): RecyclerView.Adapter<ExhibitImagesAdapter.ExhibitViewHolder>(){
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExhibitViewHolder {
        context = parent.context!!
        val view = LayoutInflater.from(context).inflate(R.layout.exhibit_image_item, parent, false)
        return ExhibitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ExhibitViewHolder, position: Int) {
        Glide.with(context)
            .load(imageList[position])
            .centerCrop()
            .into(holder.image)

    }

    class ExhibitViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image)
    }

}

