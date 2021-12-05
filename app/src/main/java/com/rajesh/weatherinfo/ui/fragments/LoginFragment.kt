package com.rajesh.weatherinfo.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.rajesh.weatherinfo.R
import com.rajesh.weatherinfo.base.BaseFragment
import com.rajesh.weatherinfo.databinding.LoginFragmentBinding
import com.rajesh.weatherinfo.supportFiles.Constants
import com.rajesh.weatherinfo.supportFiles.PrefDataType
import com.rajesh.weatherinfo.ui.activity.MainActivity
import com.rajesh.weatherinfo.ui.viewmodel.LoginViewModel
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<LoginFragmentBinding>() {


    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
    }

    override fun getLayoutId(): Int {
        return R.layout.login_fragment
    }

    override fun onReady() {
        (activity as? MainActivity)?.hideActionBar()
        (activity as? MainActivity)?.setFullScreenMode()
        viewModel.mLoginStatus.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                if(it){
                    showToast("userLogged in successfully")
                    view?.findNavController()
                        ?.navigate(R.id.action_loginFragment_to_userListFragment)
                }else{
                    showToast("user login process failed")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        isLoggedIn()
        //loadData()
        mBinding.loginbutton.setOnClickListener {
            if (!viewModel.checkEmail(mBinding.email.text.toString())) {
                mBinding.email.error = "Please enter a valid email address."
                mBinding.email.requestFocus()
                return@setOnClickListener

            } else if (!viewModel.checkEmpty(mBinding.email.text.toString())) {
                mBinding.email.error = "Please enter a email address."
                mBinding.email.requestFocus()
                return@setOnClickListener

            } else if (!(mBinding.email.text.toString().equals("testapp@google.com", true))) {
                mBinding.email.error = "Please enter a suggested email address."
                mBinding.email.requestFocus()
                return@setOnClickListener

            } else if (!viewModel.checkEmpty(mBinding.password.text.toString())) {
                mBinding.password.error = "Password Should be filled"
                mBinding.password.requestFocus()
                return@setOnClickListener
            } else if (!(mBinding.password.text.toString().equals("test@123456", true))) {
                mBinding.password.error = "Please enter a valid password."
                mBinding.password.requestFocus()
                return@setOnClickListener

            } else {
               // showToast("valid details")
                viewModel.saveUser(email = mBinding.email.text.toString(),password = mBinding.password.text.toString(),mCustomSharedPreferences)
            }
        }
    }

    fun loadData() {
        if (context?.let { hasInternetConnected(it) } == true) {
            viewModel.getData()
        } else {
            showToast("No internet or wifi enabled.")
        }
    }

    private fun isLoggedIn(){
        if(mCustomSharedPreferences.getValue(Constants.USER_STATE,PrefDataType.BOOLEAN) as Boolean){
        if(mCustomSharedPreferences.getValue(Constants.IS_LOGGED,PrefDataType.BOOLEAN) as Boolean){
            //showToast("User Logged")
            view?.findNavController()
                ?.navigate(R.id.action_loginFragment_to_userListFragment)
        }else{
           // showToast("user not logged in")
        }
        }else{
            if(mCustomSharedPreferences.getValue(Constants.IS_LOGGED,PrefDataType.BOOLEAN) as Boolean){
                val user = mCustomSharedPreferences.getValue(Constants.USER_EMAIL,PrefDataType.STRING) as String
                val password = mCustomSharedPreferences.getValue(Constants.USER_PASSWORD,PrefDataType.STRING) as String
                mBinding.previousUser.text = "Previous User : $user \n click on email to login again"

                mBinding.previousUser.setOnClickListener {
                    viewModel.saveUser(email = user.toString(),password = password.toString(),mCustomSharedPreferences)
                }
            }else{

            }
        }
    }

}