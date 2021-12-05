package com.rajesh.weatherinfo.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rajesh.weatherinfo.R
import com.rajesh.weatherinfo.data.model.UserModel
import com.rajesh.weatherinfo.databinding.UsersViewBinding

class UserListAdapter(
    val onSelect: (selectedUser: UserModel) -> Unit,
    val onRemove: (selectedUser: UserModel) -> Unit
) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var userList: ArrayList<UserModel> = arrayListOf<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.users_view,
            parent,
            false
        )
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = userList[position]
        holder.mBinding?.apply {
            firstName.text = "FirstName : ${data.firstName.toString()}"
            lastName.text = "LastName : " + data.LastName.toString()
            email.text = "Email : " + data.Email.toString()
            userItem.setOnClickListener {
                onSelect(data)
            }

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addData(list: List<UserModel>) {
        this.userList = list as ArrayList<UserModel>

        notifyDataSetChanged()
    }

    fun addSingleUser(user: UserModel) {
          userList.add(0, user)
         notifyItemInserted(0)

         //notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        onRemove(userList[position])
        userList.removeAt(position)
        notifyDataSetChanged()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mBinding = DataBindingUtil.bind<UsersViewBinding>(itemView)
    }
}