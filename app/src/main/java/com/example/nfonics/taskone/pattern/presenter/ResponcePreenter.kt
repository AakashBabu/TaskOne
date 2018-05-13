package com.example.root.thoughtworkslearning.pattern.presenter

import com.example.nfonics.taskone.pattern.model.DataModel
import com.example.root.thoughtworkslearning.api.MyCallback
import com.example.root.thoughtworkslearning.api.WebClient


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



}