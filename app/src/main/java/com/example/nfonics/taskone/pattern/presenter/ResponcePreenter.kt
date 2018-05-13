package com.example.root.thoughtworkslearning.pattern.presenter

import com.example.nfonics.taskone.pattern.model.DataModel
import com.example.root.thoughtworkslearning.api.MyCallback
import com.example.root.thoughtworkslearning.api.WebClient
import java.util.*


class ResponcePreenter(val viewSupport : ResponceSupport,val dataSet: WebClient ){

    lateinit var callback: MyCallback

    fun fetChDataFromAPI(){
        viewSupport.showLoader()
        dataSet.fetchData(object : MyCallback{
            override fun onSuccess(baseModel: DataModel?){
                viewSupport.hideLoader()
                viewSupport.upDateData(baseModel)
            }

            override fun onError() {
                viewSupport.hideLoader()
                viewSupport.errorView()
            }

        })
    }

    fun sortFunction(lister : ArrayList<DataModel.Products>){
        Collections.sort(lister, object : Comparator<DataModel.Products> {
            override fun compare(c1: DataModel.Products?, c2: DataModel.Products?): Int {
                val firstVal = Integer.parseInt(c1?.actual_price?.replace("R$","")?.replace(",","")?.trim())
                val seconVal = Integer.parseInt(c1?.actual_price?.replace("R$","")?.replace(",","")?.trim())
                if (firstVal > seconVal) return -1;
                if (firstVal < seconVal) return 1;
                return 0;
            }
        })

    }

    fun sortDiscount(lister: ArrayList<DataModel.Products>) {
        Collections.sort(lister, object : Comparator<DataModel.Products>{
            override fun compare(c1: DataModel.Products?, c2: DataModel.Products?): Int {
                val firstVal = Integer.parseInt(c1?.discount_percentage?.replace("%",""))
                val seconVal = Integer.parseInt(c1?.discount_percentage?.replace("%",""))
                if (firstVal > seconVal) return -1;
                if (firstVal < seconVal) return 1;
                return 0;
            }
        })

    }


}