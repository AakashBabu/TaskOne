package com.example.nfonics.taskone.pattern.dialoge

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import com.example.nfonics.taskone.R
import com.example.nfonics.taskone.pattern.model.DataModel
import com.example.nfonics.taskone.pattern.presenter.SortAction
import kotlinx.android.synthetic.main.sort_dialoge.*

class SortDialoge (context: Context?, val data: SortAction) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sort_dialoge);

        rbt_discount.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                data.sortDiscount()
                dismiss()
            }
        })

        rbt_price.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                data.sortPrice()
                dismiss()
            }
        })

//        rbt_actual.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                data.sortActualPrice()
//                dismiss()
//            }
//        })



    }
}