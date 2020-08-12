package com.e.vilinks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_content_list_links.*
import kotlinx.android.synthetic.main.activity_list_links.*

class LinkListActivity : AppCompatActivity() {

     val listDataManager by lazy { ListDataManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_links)
        setSupportActionBar(toolbar)

        val lists = listDataManager.readList()
        list_recycler.layoutManager = LinearLayoutManager(this)
        list_recycler.adapter = LinksAdapter(lists)


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
            val adapter = list_recycler.adapter as LinksAdapter
            val list = LinkList(titleEditText.text.toString())
            listDataManager.saveList(list)
            adapter.addList(list)
            dialog.dismiss()
        }

        myDialog.create().show()

    }
}