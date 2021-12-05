package com.rajesh.weatherinfo.ui.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rajesh.weatherinfo.R
import com.rajesh.weatherinfo.base.BaseFragment
import com.rajesh.weatherinfo.data.model.UserModel
import com.rajesh.weatherinfo.data.rest.DatabaseBuilder
import com.rajesh.weatherinfo.data.rest.DatabaseHelperImpl
import com.rajesh.weatherinfo.databinding.UserListFragmentBinding
import com.rajesh.weatherinfo.ui.adapters.UserListAdapter
import com.rajesh.weatherinfo.ui.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.add_user.view.*
import kotlinx.android.synthetic.main.header_for_all.view.*
import org.koin.android.ext.android.inject

class UserListFragment : BaseFragment<UserListFragmentBinding>() {

    private val viewModel: UserListViewModel by inject()
    private lateinit var  mUserListAdapter : UserListAdapter
    private val dbHelper = context?.let { DatabaseBuilder.getInstance(it) }?.let {
        DatabaseHelperImpl(
            it
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)
        mUserListAdapter = UserListAdapter(onSelect = {
            if(it!= null){
                view?.findNavController()
                    ?.navigate(R.id.action_userListFragment_to_weatherInfo)
            }
        },onRemove = {
            context?.let { it1 -> viewModel.removeUser(it, it1) }
        })

    }


    override fun onReady() {
        //showToast("UserList ready")
        mBinding.root.back_button_view.visibility = View.GONE
        mBinding.root.add_user.setOnClickListener {
           // showToast("user clicked")
            alertDialogUser("Add User")
        }
        mBinding.rvUsers.apply {
            adapter = mUserListAdapter

        }
        val swipeToDelete = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
               mUserListAdapter.removeItem(viewHolder.adapterPosition)
                mUserListAdapter.notifyItemRemoved(viewHolder.adapterPosition)

            }
        }
        val helper = ItemTouchHelper(swipeToDelete)
        helper.attachToRecyclerView(mBinding.rvUsers)

        viewModel.mInsertStatus.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                if(it){
                    showToast("user inserted successfully")
                }else{
                    showToast("user insertion failed")
                }
            }
        })
        viewModel.mRemoveStatus.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                if(it){
                    showToast("user Removed successfully")
                }else{
                    showToast("user Removing failed")
                }
            }
        })
        viewModel.mUserData.observe(viewLifecycleOwner, Observer {
            if(it !=null){
                if(!it.isEmpty()){
                    mUserListAdapter.addData(it)
                }else{
                    showToast("No users data available")
                }
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.user_list_fragment
    }

    private fun alertDialogUser(title: String) {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.add_user, null)
        val builder =
            MaterialAlertDialogBuilder(requireContext())
                //.setTitle(title)
                .setCancelable(false)
                .setView(mDialogView)


        val alertDialog = builder.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.headerText.text = title

        mDialogView.Cancel.setOnClickListener {
            alertDialog.dismiss()
           // showToast("cancel clicked")
        }

        mDialogView.Save.setOnClickListener {
            alertDialog.dismiss()
            val userDetails = UserModel(0,mDialogView.firstName.text.toString(),mDialogView.lastName.text.toString(),mDialogView.email.text.toString())
            mUserListAdapter.addSingleUser(userDetails)
            mBinding.rvUsers.scrollToPosition(0)
            context?.let { it1 -> viewModel.saveUser(data = userDetails, it1) }
            //mUserListAdapter.notifyItemInserted(0)


        }
        //alertDialog.setCancelable(true)
    }

    override fun onResume() {
        super.onResume()
        context?.let { viewModel.getData(context = it) }
    }
}
abstract class SwipeToDelete:ItemTouchHelper.Callback(){
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeFlag)

    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
       // Log.d("swiped","swiped")
    }
}