package com.example.professionworker.ui.fragment.register

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentSplashBinding
import com.example.professionworker.databinding.FragmentSubscribationBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.ui.bottomShet.OnClickSubscribationBottomSheetFragment
import com.example.professionworker.ui.bottomShet.OnClickSucessBottomSheetFragment
import com.example.professionworker.ui.bottomShet.SubscribationBottomSheetFragment
import com.example.professionworker.ui.bottomShet.SubscribationSucessBottomSheetFragment
import com.example.professionworker.util.Constants

class SubscribationFragment : BaseFragment<FragmentSubscribationBinding>() {


    override fun onFragmentReady() {
        binding.btnSubscribe.setOnClickListener {
            showSubscribationsBotttomSheetFragment()

        }
    }

    fun openMain() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }

    private fun showSubscribationsBotttomSheetFragment() {
        SubscribationBottomSheetFragment.newInstance(object : OnClickSubscribationBottomSheetFragment {
            override fun onClick(choice: String) {
                if (choice.equals(Constants.YES)) {
                    showSucessSubscribationBotttomSheetFragment()
                } else {

                }
            }


        }).show(childFragmentManager, SubscribationBottomSheetFragment::class.java.canonicalName)
    }

    private fun showSucessSubscribationBotttomSheetFragment() {
        SubscribationSucessBottomSheetFragment.newInstance(object : OnClickSucessBottomSheetFragment {
            override fun onClick(choice: String) {
                if (choice.equals(Constants.YES)) {
                    openMain()
                } else {

                }
            }


        }).show(childFragmentManager, SubscribationSucessBottomSheetFragment::class.java.canonicalName)
    }
}