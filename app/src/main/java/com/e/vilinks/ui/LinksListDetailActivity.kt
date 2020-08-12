package com.e.vilinks.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.vilinks.R
import com.e.vilinks.model.LinksTopics
import com.e.vilinks.utils.INTENT_LIST_KEY

 lateinit var list: LinksTopics

class LinksListDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links_list_detail)

        list = intent.getParcelableExtra(INTENT_LIST_KEY) as LinksTopics
        title = list.name
    }
}