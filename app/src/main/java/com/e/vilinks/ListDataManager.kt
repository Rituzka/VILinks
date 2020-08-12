package com.e.vilinks

import android.content.Context
import androidx.preference.PreferenceManager


class ListDataManager(private val context: Context) {

    fun saveList(list: LinkList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putStringSet(list.name, list.links.toHashSet())
        sharedPrefs.apply()
    }

    fun readList(): ArrayList<LinkList> {
        val linkLists = ArrayList<LinkList>()
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all

        for (linkList in contents) {
            val linkItem = ArrayList(linkList.value as HashSet<String>)
            val list = LinkList(linkList.key, linkItem)
            linkLists.add(list)
        }

        return linkLists
    }
}