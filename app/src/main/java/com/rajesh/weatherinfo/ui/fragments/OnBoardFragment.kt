package com.rajesh.weatherinfo.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.rajesh.weatherinfo.R
import com.rajesh.weatherinfo.base.BaseFragment
import com.rajesh.weatherinfo.databinding.OnBoardFragmentBinding
import com.rajesh.weatherinfo.supportFiles.Constants
import com.rajesh.weatherinfo.supportFiles.PrefDataType
import com.rajesh.weatherinfo.ui.activity.MainActivity
import org.koin.android.ext.android.inject

class OnBoardFragment : BaseFragment<OnBoardFragmentBinding>() {

    private val viewModel: OnBoardViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
    }

    override fun onReady() {
        (activity as? MainActivity)?.hideActionBar()
        (activity as? MainActivity)?.setFullScreenMode()
        mBinding.onBoardlogin.setOnClickListener {
            mCustomSharedPreferences.addValue(Constants.ON_BOARD, true)
            view?.findNavController()
                ?.navigate(R.id.action_onBoardFragment_to_loginFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        getOnBoard()
    }
    override fun getLayoutId(): Int {
        return R.layout.on_board_fragment
    }

    fun getOnBoard() {
        if (mCustomSharedPreferences.getValue(
                Constants.ON_BOARD,
                PrefDataType.BOOLEAN
            ) as Boolean
        ) {
            Log.d("true","true")
            view?.findNavController()
                ?.navigate(R.id.action_onBoardFragment_to_loginFragment)
        } else {
            Log.d("false","false")
        }

    }
}