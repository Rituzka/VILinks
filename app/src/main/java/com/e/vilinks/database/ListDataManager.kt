package com.e.vilinks.database

import android.content.Context
import androidx.preference.PreferenceManager
import com.e.vilinks.model.Link
import com.e.vilinks.model.Topics


class ListDataManager(private val context: Context) {

    fun saveList(topicTitleList: Topics) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putStringSet(topicTitleList.name, topicTitleList.links.toHashSet())
        sharedPrefs.apply()
    }

    fun readList(): ArrayList<Topics> {
        val linkLists = ArrayList<Topics>()
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all

        for (linkList in contents) {
            val linkItem = ArrayList(linkList.value as HashSet<String>)
            val list = Topics(linkList.key, linkItem)
            linkLists.add(list)
        }

        return linkLists
    }

    fun deleteItem(topicTitleList: Topics) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.remove(topicTitleList.name)
        sharedPrefs.apply()
    }
}