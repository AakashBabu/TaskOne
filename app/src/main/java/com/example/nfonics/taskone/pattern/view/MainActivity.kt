package com.example.nfonics.taskone.pattern.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ListAdapter
import com.example.nfonics.taskone.R
import com.example.nfonics.taskone.pattern.adapter.DataAdapter
import com.example.nfonics.taskone.pattern.dialoge.PopupDialoge
import com.example.nfonics.taskone.pattern.dialoge.SortDialoge
import com.example.nfonics.taskone.pattern.model.DataModel
import com.example.nfonics.taskone.pattern.presenter.Clicked
import com.example.nfonics.taskone.pattern.presenter.SortAction
import com.example.root.thoughtworkslearning.Loader
import com.example.root.thoughtworkslearning.api.WebClient
import com.example.root.thoughtworkslearning.pattern.presenter.ResponcePreenter
import com.example.root.thoughtworkslearning.pattern.presenter.ResponceSupport

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import java.util.Objects.compare

class MainActivity : AppCompatActivity(), ResponceSupport, Clicked, SortAction {

    lateinit var presenter : ResponcePreenter
    lateinit var loading : Loader
    lateinit var context : Context
    lateinit var adapter_set : DataAdapter
    lateinit var master_data : List<DataModel.Products>
    val lister = ArrayList<DataModel.Products>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        loading = Loader(this);
        context = this
        listview.layoutManager = LinearLayoutManager(this)
//        adapter_set = DataAdapter(null,context,this)
//        listview.adapter = adapter_set

        filter.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().length != 0)
                    serchFilter(s.toString())
                else
                    filterClass()
            }

        })

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
        filter.visibility = GONE
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> onSaleClass()
            R.id.action_filter -> filterClass()
            R.id.action_sort -> sortClass()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onSaleClass(): Boolean {
        val lister = ArrayList<DataModel.Products>()
        for(filter in master_data){
            if(filter.on_sale) {
                lister.add(filter)
            }
        }

        listview.adapter = DataAdapter(lister,context,this)
//        adapter_set.loadFilter(lister)

        return true
    }

    private fun filterClass(): Boolean {
        filter.visibility = VISIBLE
        lister.clear()
        lister.addAll(master_data)
        listview.adapter = DataAdapter(lister,context,this)
        return true
    }

    fun sortClass() : Boolean{
        SortDialoge(context,this).show()
        return true
    }


    override fun upDateData(data: DataModel?) {
        master_data = data?.products!!
        lister.clear()
        lister.addAll(master_data)
        listview.adapter = DataAdapter(lister,context,this)
//        adapter_set.reloadData(data)

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
        PopupDialoge(context,data).show()
    }

    override fun sortDiscount() {
        lister.clear()
        for(filter in master_data){
            if(filter.on_sale) {
                lister.add(filter)
            }
        }
        presenter.sortDiscount(lister)
        listview.adapter = DataAdapter(lister,context,this)
//        adapter_set.loadFilter(lister)
    }

    fun serchFilter(str : String){
        lister.clear()
        for (d in master_data) {
            if (d.name.toLowerCase().contains(str.toLowerCase())) {
                lister.add(d)
            }
        }
        listview.adapter = DataAdapter(lister,context,this)
//        adapter_set.reloadDataSet(lister)
    }

    override fun sortPrice() {
        lister.clear()
        lister.addAll(master_data)
        presenter.sortFunction(lister)
        listview.adapter = DataAdapter(lister,context,this)
//        adapter_set.loadFilter(lister)

    }

    override fun sortActualPrice() {

    }

}
