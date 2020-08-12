package com.e.vilinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.link_item_view_holder.view.*


class LinksAdapter(val linkList: ArrayList<LinkList>) : RecyclerView.Adapter<LinksAdapter.LinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.link_item_view_holder, parent, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount() = linkList.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.linkPosition.text = (position + 1).toString()
        holder.linkName.text = linkList[position].name
    }

    fun addList(item: LinkList){
        linkList.add(item)
        notifyItemInserted(linkList.size - 1)
    }

    class LinkViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var linkPosition = itemView.itemNumber
        var linkName = itemView.itemString

    }
}