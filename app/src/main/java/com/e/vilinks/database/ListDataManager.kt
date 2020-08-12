package com.e.vilinks.database

import android.content.Context
import androidx.preference.PreferenceManager
import com.e.vilinks.model.LinksTopics


class ListDataManager(private val context: Context) {

    fun saveList(topicTitleList: LinksTopics) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putStringSet(topicTitleList.name, topicTitleList.links.toHashSet())
        sharedPrefs.apply()
    }

    fun readList(): ArrayList<LinksTopics> {
        val linkLists = ArrayList<LinksTopics>()
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all

        for (linkList in contents) {
            val linkItem = ArrayList(linkList.value as HashSet<String>)
            val list = LinksTopics(linkList.key, linkItem)
            linkLists.add(list)
        }

        return linkLists
    }
}