package com.e.vilinks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.link_item_view_holder.view.*

class LinksAdapter(): RecyclerView.Adapter<LinksAdapter.LinkViewHolder>() {

    private var listLinks = mutableListOf("link1", "link2", "link3", "link4", "link5")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.link_item_view_holder, parent, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount() = listLinks.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.linkPosition.text = (position + 1).toString()
        holder.linkName.text = listLinks[position]
    }

    fun addLink(newItem: String) {
        if(newItem.isEmpty()) {
        listLinks.add("Link "+ (listLinks.size + 1))
        }else {
            listLinks.add(newItem)
        }
        notifyDataSetChanged()
    }

    class LinkViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var linkPosition = itemView.itemNumber
        var linkName = itemView.itemString

    }
}