package com.e.vilinks.database

import android.content.Context
import androidx.preference.PreferenceManager
import com.e.vilinks.model.Link


class SharePrefsListLinks (private val context: Context) {

    fun saveLinkList(linkList: Link) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putString("link", linkList.url)
        sharedPrefs.apply()
    }

    fun readLinkList(): ArrayList<Link> {
        val linkLists = ArrayList<Link>()
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all

        return linkLists
    }
}



