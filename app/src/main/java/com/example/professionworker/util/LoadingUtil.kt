package com.example.professionworker.util

import android.app.Dialog
import android.content.Context
import com.example.professionworker.R

class LoadingUtil(context: Context) : Dialog(context) {

    fun showLoading() {
        show()
    }

    fun hideLoading() {
        dismiss()
        cancel()
    }

    init {
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        window!!.setBackgroundDrawableResource(R.color.transparent)
    }
}