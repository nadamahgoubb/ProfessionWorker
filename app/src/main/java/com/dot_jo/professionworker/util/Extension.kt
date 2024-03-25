package com.dot_jo.professionworker.util

import android.util.Patterns
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

object Extension {


    fun isEmailValid(email: String?): Boolean =
        !email.isNullOrEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches()


}