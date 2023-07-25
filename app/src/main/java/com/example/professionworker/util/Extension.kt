package com.example.professionworker.util

import android.util.Patterns
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

object Extension {

    fun Fragment.withFragment(dataBinding: ViewDataBinding) {
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    fun isEmailValid(email: String?): Boolean =
        !email.isNullOrEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches()


}