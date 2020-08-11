package com.e.vilinks

import android.content.Context
import androidx.preference.PreferenceManager


class ListDataManager(private val context: Context) {

    fun saveList(list: LinkList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putStringSet(list.name, list.links.toHashSet())
        sharedPrefs.apply()
    }
}