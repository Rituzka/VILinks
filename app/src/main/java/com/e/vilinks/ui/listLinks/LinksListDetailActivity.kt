package com.e.vilinks.ui.listLinks


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.vilinks.R
import com.e.vilinks.model.Topics
import com.e.vilinks.ui.listTopics.TitleTopicsActivity
import com.e.vilinks.utils.INTENT_DETAIL_REQUEST_CODE
import com.e.vilinks.utils.INTENT_LIST_KEY
import kotlinx.android.synthetic.main.activity_links_list_detail.*


class LinksListDetailActivity : AppCompatActivity(), LinksListAdapter.ItemLinkClickListener {

    private lateinit var list: Topics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links_list_detail)
    
        list = intent.getParcelableExtra(INTENT_LIST_KEY) as Topics
        title = list.name
        
        //configure recyclerView
        list_links.layoutManager = LinearLayoutManager(this)
        list_links.adapter = LinksListAdapter(list.links, this)

        btn_link.setOnClickListener {
            showCreateLinkDialog()
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(INTENT_LIST_KEY, list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    private fun showCreateLinkDialog() {
        val url = EditText(this)
        url.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.paste_url)
            .setView(url)
            .setPositiveButton(R.string.positiveButtonName2) {
                dialog, _ ->
            val link = url.text.toString()
            list.links.add(link)
            dialog.dismiss()
        }

            .create()
            .show()
    }

    override fun onLinkClicked(link:String) {
       goToWeb()
    }

    private fun goToWeb() {
            val itemLink = Intent(this, TitleTopicsActivity::class.java)
            startActivity(itemLink)
    }
}
