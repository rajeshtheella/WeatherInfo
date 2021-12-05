package com.rajesh.weatherinfo.base

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rajesh.weatherinfo.supportFiles.CustomSharedPreferences
import org.koin.android.ext.android.inject

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var mBinding: T
    lateinit var mBaseViewModel: BaseViewModel
    val mCustomSharedPreferences: CustomSharedPreferences by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        onReady()
        return mBinding.root

    }

    abstract fun onReady()
    abstract fun getLayoutId(): Int
    fun setViewModel(mBaseViewModel: BaseViewModel){
        this.mBaseViewModel = mBaseViewModel

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    fun showToast(s: String) {
        val toast=   Toast.makeText(context, s, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()


    }
    fun hasInternetConnected(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isMetered = cm.isActiveNetworkMetered
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}