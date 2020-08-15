package com.e.vilinks.ui.listTopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.vilinks.model.Topics
import com.e.vilinks.database.ListDataManager
import com.e.vilinks.R
import com.e.vilinks.ui.listLinks.LinksListDetailActivity
import com.e.vilinks.utils.INTENT_DETAIL_REQUEST_CODE
import com.e.vilinks.utils.INTENT_LIST_KEY
import kotlinx.android.synthetic.main.activity_content_list_topics.*
import kotlinx.android.synthetic.main.activity_list_topics.*

class TitleTopicsActivity : AppCompatActivity(),
    TitleTopicsAdapter.linksTopicListener {

     val listDataManager =  ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_topics)
        setSupportActionBar(toolbar)

        val lists = listDataManager.readList()
        list_topics.layoutManager = LinearLayoutManager(this)
        list_topics.adapter =
            TitleTopicsAdapter(lists, this)


        addTopic.setOnClickListener {
            showCreateTopicDialog()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == INTENT_DETAIL_REQUEST_CODE) {
            data?.let {
                val list = data.getParcelableExtra<Topics>(INTENT_LIST_KEY)!!
                listDataManager.saveList(list)
                updateList()
            }
        }
    }

    private fun updateList() {
      val lists = listDataManager.readList()
        list_topics.adapter = TitleTopicsAdapter(lists, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_help -> true
            R.id.action_logout -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateTopicDialog() {
        val dialogTitle = getString(R.string.topic_title)
        val positiveButtonTitle = getString(R.string.positiveButtonName)
        val myDialog = AlertDialog.Builder(this)
        val titleEditText = EditText(this)
        titleEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        myDialog.setTitle(dialogTitle)
        myDialog.setView(titleEditText)
        myDialog.setPositiveButton(positiveButtonTitle) {
            dialog, _ ->
            val adapter = list_topics.adapter as TitleTopicsAdapter
            val list =
                Topics(titleEditText.text.toString())
            listDataManager.saveList(list)
            adapter.addList(list)
            dialog.dismiss()
            showListLinks(list)
        }

        myDialog.create().show()

    }

    private fun showListLinks(topicTitleList: Topics) {
        val titleItem = Intent(this, LinksListDetailActivity::class.java)
        titleItem.putExtra(INTENT_LIST_KEY, topicTitleList)
         startActivityForResult(titleItem, INTENT_DETAIL_REQUEST_CODE)
    }

    override fun onTitleTopicClick(list: Topics) {
        showListLinks(list)
    }
}