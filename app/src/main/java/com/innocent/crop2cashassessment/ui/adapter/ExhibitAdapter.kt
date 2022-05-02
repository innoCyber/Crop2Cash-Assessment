package com.innocent.crop2cashassessment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innocent.crop2cashassessment.R
import com.innocent.crop2cashassessment.model.Exhibit

class ExhibitAdapter (var exhibitList: List<Exhibit>): RecyclerView.Adapter<ExhibitAdapter.ExhibitViewHolder>(){

    lateinit var context: Context
    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExhibitViewHolder {
        context = parent.context!!
        val view = LayoutInflater.from(context).inflate(R.layout.exhibit_item, parent, false)
        return ExhibitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exhibitList.size
    }

    override fun onBindViewHolder(holder: ExhibitViewHolder, position: Int) {
        holder.title.text = exhibitList[position].title

        val layoutManager = LinearLayoutManager(
            holder.imagesRecyclerview.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager.initialPrefetchItemCount = exhibitList[position].images.size

        val subItemAdapter = ExhibitImagesAdapter(exhibitList[position].images)
        holder.imagesRecyclerview.layoutManager = layoutManager
        holder.imagesRecyclerview.adapter = subItemAdapter
        holder.imagesRecyclerview.setRecycledViewPool(viewPool)

    }

    class ExhibitViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val imagesRecyclerview: RecyclerView = itemView.findViewById(R.id.images_recyclerview)

    }

}