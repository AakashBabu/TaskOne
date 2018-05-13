package com.example.root.thoughtworkslearning.api

import com.example.nfonics.taskone.pattern.model.DataModel

interface MyCallback {
        fun onSuccess(baseModel: DataModel?)
        fun onError()

}