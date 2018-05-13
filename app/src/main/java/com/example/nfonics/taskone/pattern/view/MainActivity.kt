package com.example.nfonics.taskone.pattern.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ListAdapter
import com.example.nfonics.taskone.R
import com.example.nfonics.taskone.pattern.adapter.DataAdapter
import com.example.nfonics.taskone.pattern.model.DataModel
import com.example.nfonics.taskone.pattern.presenter.Clicked
import com.example.root.thoughtworkslearning.Loader
import com.example.root.thoughtworkslearning.api.WebClient
import com.example.root.thoughtworkslearning.pattern.presenter.ResponcePreenter
import com.example.root.thoughtworkslearning.pattern.presenter.ResponceSupport

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), ResponceSupport, Clicked {



    lateinit var presenter : ResponcePreenter
    lateinit var loading : Loader
    lateinit var context : Context
    lateinit var adapter_set : DataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        loading = Loader(this);
        context = this
        listview.layoutManager = LinearLayoutManager(this)
        adapter_set = DataAdapter(null,context,this)
        listview.adapter = adapter_set

    }

    override fun onResume() {
        super.onResume()
        presenter = ResponcePreenter(this, WebClient())
        presenter.fetChDataFromAPI()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun upDateData(data: DataModel?) {
        adapter_set.reloadData(data)

    }

    override fun errorView() {
        print("error")
    }

    override fun showLoader() {
        loading.show()
    }

    override fun hideLoader() {
        loading.hide()

    }

    override fun selectedItem(data: DataModel.Products?) {

    }

}
