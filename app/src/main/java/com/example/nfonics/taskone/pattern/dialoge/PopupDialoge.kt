package com.example.nfonics.taskone.pattern.dialoge

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.Window
import android.widget.TextView
import com.example.nfonics.taskone.R
import com.example.nfonics.taskone.pattern.model.DataModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pop_up.*
import kotlinx.android.synthetic.main.row_size.view.*

class PopupDialoge(context: Context?,val data: DataModel.Products?) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pop_up);
        pop_title.text = data?.name
        pop_price.text = data?.regular_price
        if(data?.image?.trim()?.length != 0)
            Picasso.get().load(data?.image).into(pop_image)

        if(data?.on_sale!!) {
            pop_stock.text = "On Sale : Available"
            pop_stock.setTextColor(context.resources.getColor(R.color.green))
        }else {
            pop_stock.text = "On Sale : Not Available"
            pop_stock.setTextColor(context.resources.getColor(R.color.red))
        }

        if(data?.discount_percentage.trim().length != 0)
            pop_discount.text = "Discount Available :"+data?.discount_percentage
        else
            pop_discount.visibility = GONE

        if(data?.sizes != null) {
            for (sez in data.sizes) {
                if (sez.available) {
                    val view = LayoutInflater.from(context).inflate(R.layout.row_size,null);
                    view.size__.text = sez.size
                    ll_size.addView(view)
                }
            }
        }
    }
}