package com.example.professionworker.ui.fragment.register


 import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
 import com.example.profession.data.dataSource.response.BankItemResponse
 import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentRegisterBankAccountBinding
import com.example.professionworker.ui.activity.MainActivity
 import com.example.professionworker.ui.dialog.BankListener
 import com.example.professionworker.ui.dialog.BanksDialog
 import com.example.professionworker.ui.dialog.CategoriesDialog
 import com.example.professionworker.ui.fragment.login.AuthViewModel
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.ext.showActivity
import com.example.professionworker.util.observe
 import com.example.professionworker.ui.fragment.login.AuthAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterBankAccountFragment : BaseFragment<FragmentRegisterBankAccountBinding>() {
    private val mViewModel: AuthViewModel by activityViewModels()
    var bankId =""
    override fun onFragmentReady() {

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

    binding.lytHeader.ivBack.setOnClickListener {

        activity?.onBackPressed()
    }
        binding.etBank.setOnClickListener {

            mViewModel.getBanks()
        }
  binding.btnRegister.setOnClickListener {

            mViewModel.validateBankRegisteration(binding.etAccountId.text.toString(),
                binding.etAccountName.text.toString(),bankId,binding.etIpan.text.toString(),
                )
        }

        mViewModel.apply {
        observe(viewState) {
            handleViewState(it)
        }
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

            is AuthAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }
            is AuthAction.RegisterationSuccess -> {
                showProgress(false)
                showActivity(MainActivity::class.java, clearAllStack = true)
            }
            is AuthAction.ShowBanks -> {

                action.data.banks?.let {

                    openBanksDialog(it)


                }
            }

            else -> {

            }
        }
    }
    fun openBanksDialog(data: ArrayList<BankItemResponse>) {
        BanksDialog.newInstance(object : BankListener {
            override fun onBankClicked(item: BankItemResponse?) {
                bankId= item?.id.toString()

                binding.etBank.setText(item?.name)            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }



}