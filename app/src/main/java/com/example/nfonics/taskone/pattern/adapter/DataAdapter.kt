package com.example.nfonics.taskone.pattern.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nfonics.taskone.R
import com.example.nfonics.taskone.pattern.model.DataModel
import com.example.nfonics.taskone.pattern.presenter.Clicked
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_data.view.*
import java.util.ArrayList

/**
 * Created by nfonics on 5/13/18.
 */
class DataAdapter(var items : MutableList<DataModel.Products>?, val context: Context, val list : Clicked) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        if (items != null) {
            return items?.size!!
        }
        return 0
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_data, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.title?.text = items?.get(position)?.name
        holder?.price?.text = items?.get(position)?.actual_price
        holder?.prent?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                list.selectedItem(items?.get(position))
            }

        })


        if(items?.get(position)?.image?.trim()?.length != 0)
            Picasso.get().load(items?.get(position)?.image).into(holder?.image)
        else
            holder?.image?.setImageDrawable(context.resources.getDrawable(R.drawable.ic_launcher_background))
    }

    fun reloadData(new_items: DataModel?){
        items = new_items?.products
        notifyDataSetChanged()
    }

    fun reloadDataSet(new_items: ArrayList<DataModel.Products>){
        items = new_items
        notifyDataSetChanged()
    }

    fun loadFilter(lister: MutableList<DataModel.Products>){
        items?.clear()
        items = lister
        notifyDataSetChanged()

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val image = view.row_image
    val title = view.row_title
    val price = view.row_price
    val prent = view.parent_click
}