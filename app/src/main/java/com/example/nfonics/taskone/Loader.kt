package com.example.root.thoughtworkslearning

import android.app.ProgressDialog
import android.content.Context
import android.widget.ProgressBar

class Loader(context: Context?) : ProgressDialog(context) {
    init {
        setMessage("Loading")
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}