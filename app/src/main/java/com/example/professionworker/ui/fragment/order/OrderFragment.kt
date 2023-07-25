package com.example.professionworker.ui.fragment.order

  import android.util.LayoutDirection.LTR
  import android.view.View
  import androidx.core.view.isVisible
  import androidx.fragment.app.Fragment
  import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentOrderBinding
import com.example.professionworker.ui.activity.MainActivity
import com.google.android.material.tabs.TabLayout

class OrderFragment : BaseFragment<FragmentOrderBinding>() {
    private lateinit var parent: MainActivity

    private lateinit var mSectionAdapter: SectionsPagerAdapterTabs

    private fun initViews() {
        mSectionAdapter = SectionsPagerAdapterTabs(getChildFragmentManager())
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))
        binding.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager))
         binding.tabs.layoutDirection =LTR
        binding.viewPager.setAdapter(mSectionAdapter)

    }

    override fun onFragmentReady() {
        setupUi()
        initViews()
     }
    private fun setupUi() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(true)
        parent.showSideNav(true)
        binding.toolbar.tvTitle.setText(resources.getString(R.string.orders))
        binding.toolbar.ivMenu.isVisible = true
        binding.toolbar.ivBack.visibility= View.INVISIBLE
         binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

    }
}
    private class SectionsPagerAdapterTabs(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {

        override fun getItem(position: Int): Fragment {
            return if (position == 0) {
                PreviousOrderFragment( )
            }
            else if (position == 1) CurrentOrderFragment( )
            else NewOrderFragment( )

        }

        override fun getCount(): Int {
            return 3
        }
    }

