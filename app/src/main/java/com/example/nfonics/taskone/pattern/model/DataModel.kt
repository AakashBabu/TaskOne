package com.example.nfonics.taskone.pattern.model

/**
 * Created by nfonics on 5/13/18.
 */
data class DataModel(val products: MutableList<Products>){


    inner class Products(
            val name: String,
            val image: String,
            val actual_price: String,
            val regular_price: String,
            val sizes: List<Size>,
            val on_sale: Boolean,
            val discount_percentage: String
    )

    inner class Size(
            val available: Boolean,
            val size: String
    )

}