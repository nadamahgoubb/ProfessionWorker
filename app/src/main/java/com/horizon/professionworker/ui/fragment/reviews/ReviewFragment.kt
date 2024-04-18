package com.horizon.professionworker.ui.fragment.reviews

 import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
 import com.horizon.profession.ui.adapter.ReviewsAdapter
 import com.horizon.professionworker.R
import com.horizon.professionworker.base.BaseFragment
 import com.horizon.professionworker.databinding.FragmentReviewBinding
 import com.horizon.professionworker.data.response.ReviewsResponse
 import com.horizon.professionworker.ui.activity.MainActivity
import com.horizon.professionworker.ui.fragment.login.AuthAction
import com.horizon.professionworker.ui.fragment.login.AuthViewModel
 import com.horizon.professionworker.util.ext.hideKeyboard
 import com.horizon.professionworker.util.ext.init
 import com.horizon.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    private lateinit var parent: MainActivity
    private val mViewModel: AuthViewModel by viewModels()
    lateinit var adapter: ReviewsAdapter

    override fun onFragmentReady() {
        setupToolbar()
        initAdapter()
        mViewModel.apply {
            getReviews()
            observe(viewState) {
                handleViewState(it)
            }
        }
     binding.swiperefreshHome.setOnRefreshListener {
            mViewModel.getReviews()
            if (binding.swiperefreshHome != null) binding.swiperefreshHome.isRefreshing = false
        }
    }

    private fun handleViewState(action: AuthAction) {
        when (action) {
            is AuthAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }
            is AuthAction.ShowReviews -> {
                showProgress(false)
                showData(action.data)
            }

            is AuthAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }



            else -> {

            }
        }
    }
    private fun initAdapter() {
        adapter = ReviewsAdapter()
        binding.rvReviews.init(requireContext(), adapter, 2)
    }

    private fun showData(data: ReviewsResponse) {
         if (data.reviews.size > 0) {
            binding.lytEmptyState.isVisible= false
             binding.rvReviews.isVisible= true
             adapter.list = data.reviews
            adapter.notifyDataSetChanged()
        }else{
            binding.lytEmptyState.isVisible= true
             binding.rvReviews.isVisible= false
        }
        adapter.notifyDataSetChanged()
    }

    private fun setupToolbar() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(false)
        parent.showSideNav(false)

        binding.toolbar.tvTitle.setText(resources.getString(R.string.provider_review))
        binding.toolbar.ivMenu.isVisible= false
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}