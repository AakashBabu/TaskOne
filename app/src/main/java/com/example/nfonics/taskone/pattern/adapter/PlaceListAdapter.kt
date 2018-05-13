package com.example.root.thoughtworkslearning.pattern.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


//class PlaceListAdapter(var context: Activity, var array: Array<String>): BaseAdapter() {
//    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//        val view = LayoutInflater.from(context).inflate(R.layout.row_basic,null)
//        var title = view.findViewById(R.id.title) as TextView
//        title.text = (array[p0])
//        var check = view.findViewById(R.id.checkbox) as CheckBox
//        check.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
//            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//                AppKey.city = title.text.toString()
//                context.finish()
//            }
//
//        })
//        return view
//    }
//
//    override fun getItem(p0: Int): Any {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getItemId(p0: Int): Long {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getCount(): Int {
//        return array.size
//    }
//}