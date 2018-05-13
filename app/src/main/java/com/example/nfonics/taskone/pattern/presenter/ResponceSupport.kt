package com.example.root.thoughtworkslearning.pattern.presenter

import com.example.nfonics.taskone.pattern.model.DataModel

interface ResponceSupport {

    fun upDateData(data: DataModel?)
    fun errorView()
    fun showLoader()
    fun hideLoader()

}