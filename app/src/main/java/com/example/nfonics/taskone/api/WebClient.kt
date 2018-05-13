package com.example.root.thoughtworkslearning.api

import com.example.nfonics.taskone.pattern.model.DataModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class WebClient {


    public var getApi: WebServiceAPI

    init {
        val client = OkHttpClient().newBuilder().build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        getApi = retrofit.create(WebServiceAPI :: class.java)
    }


    fun fetchData(callback: MyCallback){
        getApi.getData().enqueue((object: Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                callback.onError()
            }

            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                callback.onSuccess(response?.body() as DataModel)
            }

        }))
    }



    interface WebServiceAPI{

        @GET("59b6a65a0f0000e90471257d")
        fun getData(): Call<DataModel>
    }
}