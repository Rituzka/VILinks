package com.e.vilinks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.vilinks.model.LinksTopics
import com.e.vilinks.R
import kotlinx.android.synthetic.main.link_item_view_holder.view.*


class LinksTopicsAdapter(private val linksTopics: ArrayList<LinksTopics>, val clickListener: linksTopicListener) : RecyclerView.Adapter<LinksTopicsAdapter.LinkViewHolder>() {

    interface linksTopicListener {
        fun onTitleTopicClick(list: LinksTopics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.link_item_view_holder, parent, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount() = linksTopics.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.linkPosition.text = (position + 1).toString()
        holder.linkName.text = linksTopics[position].name
        holder.itemView.setOnClickListener {
            clickListener.onTitleTopicClick(linksTopics[position])
        }
    }

    fun addList(item: LinksTopics){
        linksTopics.add(item)
        notifyItemInserted(linksTopics.size - 1)
    }

    class LinkViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var linkPosition = itemView.itemNumber
        var linkName = itemView.itemString

    }
}