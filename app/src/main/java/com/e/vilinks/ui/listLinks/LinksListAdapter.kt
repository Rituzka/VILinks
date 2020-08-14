package com.e.vilinks.ui.listLinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.vilinks.R
import com.e.vilinks.model.LinksTopics
import kotlinx.android.synthetic.main.item_link_viewholder.view.*


class LinksListAdapter(private var list: LinksTopics): RecyclerView.Adapter<LinksListAdapter.LinksListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_link_viewholder, parent, false)
          return LinksListViewHolder(view)

    }

    override fun getItemCount() = list.links.size

    override fun onBindViewHolder(holder: LinksListViewHolder, position: Int) {
        holder.linkURL?.text = list.links[position]

    }

    class LinksListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var linkURL = itemView.txt_link

    }
}