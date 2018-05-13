package com.example.nfonics.taskone.pattern.model

/**
 * Created by nfonics on 5/13/18.
 */
data class DataModel(val products: List<Products>){


    inner class Products(
            val name: String,
            val image: String,
            val actual_price: String,
            val regular_price: String,
            val size: List<Size>
    )

    inner class Size(
            val available: Boolean,
            val size: String
    )

}