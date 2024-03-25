package com.dot_jo.professionworker.ui.fragments.walkThrough

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class SectionsPagerAdapter(fragmentActivity: WalkThrougthFragment,val  listener: OnClickSkipListener) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FirstFragment(0,listener)
            1 -> return FirstFragment(1,listener)
        }
        return FirstFragment(-1,listener)
    }
}