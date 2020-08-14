package com.e.vilinks.ui.listLinks


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.vilinks.R
import com.e.vilinks.model.LinksTopics
import com.e.vilinks.ui.listTopics.TitleTopicsAdapter
import com.e.vilinks.utils.INTENT_LIST_KEY
import kotlinx.android.synthetic.main.activity_content_list_topics.*
import kotlinx.android.synthetic.main.activity_links_list_detail.*

lateinit var list: LinksTopics

class LinksListDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links_list_detail)

        list = intent.getParcelableExtra(INTENT_LIST_KEY) as LinksTopics
        title = list.name

        //configure recyclerView
        list_links.layoutManager = LinearLayoutManager(this)
        list_links.adapter = LinksListAdapter(list)

        btn_link.setOnClickListener {
            showCreateLinkDialog()
        }


    }
    private fun showCreateLinkDialog() {

        val positiveButtonTitle = getString(R.string.positiveButtonName)

        val dialogTitle2 = getString(R.string.paste_url)
        val url = EditText(this)
        url.inputType = InputType.TYPE_TEXT_VARIATION_URI

        AlertDialog.Builder(this)
        .setTitle(dialogTitle2)
        .setView(url)
        .setPositiveButton(positiveButtonTitle) {
                dialog, _ ->
            //val adapter = list_links.adapter as LinksListAdapter
            val link = url.text.toString()
            list.links.add(link)
            //listDataManager.saveList(list)
           // adapter.addList(list)
            dialog.dismiss()
            //showListLinks(list)
        }

            .create()
            .show()
    }
}