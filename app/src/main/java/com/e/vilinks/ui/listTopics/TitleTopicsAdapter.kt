package com.e.vilinks.ui.listTopics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.vilinks.model.Topics
import com.e.vilinks.R
import kotlinx.android.synthetic.main.item_topic_viewholder.view.*


class TitleTopicsAdapter(private val titleListTopics: ArrayList<Topics>, private val clickListener: LinksTopicListener) : RecyclerView.Adapter<TitleTopicsAdapter.LinkViewHolder>() {

    interface LinksTopicListener {
        fun onTitleTopicClick(list: Topics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_topic_viewholder, parent, false)
        return LinkViewHolder(view)
    }

    override fun getItemCount() = titleListTopics.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.topicName.text = titleListTopics[position].name
        holder.itemView.setOnClickListener {
            clickListener.onTitleTopicClick(titleListTopics[position])
        }
    }

    fun addList(item: Topics){
        titleListTopics.add(item)
        notifyItemInserted(titleListTopics.size - 1)
    }

    class LinkViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var topicName: TextView = itemView.itemTopic

    }
}