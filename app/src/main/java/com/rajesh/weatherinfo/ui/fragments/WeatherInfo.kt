package com.rajesh.weatherinfo.ui.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.Observer
import com.rajesh.weatherinfo.R
import com.rajesh.weatherinfo.base.BaseFragment
import com.rajesh.weatherinfo.databinding.WeatherInfoFragmentBinding
import com.rajesh.weatherinfo.supportFiles.Constants
import com.rajesh.weatherinfo.ui.viewmodel.WeatherInfoViewModel
import kotlinx.android.synthetic.main.header_for_all.view.*
import org.koin.android.ext.android.inject

class WeatherInfo : BaseFragment<WeatherInfoFragmentBinding>() {

    private  val viewModel: WeatherInfoViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
    }
    @SuppressLint("SetTextI18n")
    override fun onReady() {
       // showToast("Ready")
        mBinding.root.back_button_view.setOnClickListener {
            findNavController().popBackStack()
        }
        mBinding.root.add_user.visibility = View.GONE
        mBinding.root.logout.visibility = View.VISIBLE
        mBinding.root.logout.setOnClickListener {
            mCustomSharedPreferences.addValue(Constants.USER_STATE,false)
            view?.findNavController()
                ?.navigate(R.id.action_weatherInfo_to_loginFragment)
        }
        viewModel.weatherDetails.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                mBinding.temp.text = "Temperature : "+ it.current?.temp.toString()
                mBinding.WeatherType.text = "Weather : "+ it.current?.weather?.get(0)?.main.toString()
                mBinding.humidity.text = "Humidity :  "+it.current?.humidity.toString()
                mBinding.windSpeed.text = "Wind Speed :  "+it.current?.wind_speed.toString()
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.weather_info_fragment
    }
    fun loadData() {
        if (context?.let { hasInternetConnected(it) } == true) {
            viewModel.getData()
        } else {
            showToast("No internet or wifi enabled.")
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}