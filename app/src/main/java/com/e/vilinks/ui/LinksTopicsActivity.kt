package com.e.vilinks.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.vilinks.model.LinksTopics
import com.e.vilinks.database.ListDataManager
import com.e.vilinks.R
import com.e.vilinks.utils.INTENT_LIST_KEY
import kotlinx.android.synthetic.main.activity_content_list_links.*
import kotlinx.android.synthetic.main.activity_list_links.*

class LinksTopicsActivity : AppCompatActivity(), LinksTopicsAdapter.linksTopicListener {

     val listDataManager by lazy { ListDataManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_links)
        setSupportActionBar(toolbar)

        val lists = listDataManager.readList()
        list_recycler.layoutManager = LinearLayoutManager(this)
        list_recycler.adapter = LinksTopicsAdapter(lists, this )


        addLink.setOnClickListener {
            showCreateTitleLinkDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateTitleLinkDialog() {
        val dialogTitle = getString(R.string.titleLink)
        val positiveButtonTitle = getString(R.string.positiveButtonName)
        val myDialog = AlertDialog.Builder(this)
        val titleEditText = EditText(this)
        titleEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        myDialog.setTitle(dialogTitle)
        myDialog.setView(titleEditText)
        myDialog.setPositiveButton(positiveButtonTitle) {
            dialog, _ ->
            val adapter = list_recycler.adapter as LinksTopicsAdapter
            val list =
                LinksTopics(titleEditText.text.toString())
            listDataManager.saveList(list)
            adapter.addList(list)
            dialog.dismiss()
            showListLinks(list)
        }

        myDialog.create().show()

    }

    private fun showListLinks(topicTitleList: LinksTopics) {
        val titleItem = Intent(this, LinksListDetailActivity::class.java)
        titleItem.putExtra(INTENT_LIST_KEY, topicTitleList)
         startActivity(titleItem)
    }

    override fun onTitleTopicClick(list: LinksTopics) {
        showListLinks(list)
    }
}