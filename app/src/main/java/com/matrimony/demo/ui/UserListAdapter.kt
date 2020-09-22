package com.matrimony.demo.ui

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrimony.demo.R
import com.matrimony.demo.listener.ItemClickListener
import com.matrimony.demo.model.ResultUserItem
import kotlinx.android.synthetic.main.item_user_list.view.*

class UserListAdapter(
    var users: ArrayList<ResultUserItem>,
    var context: Context,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
        if(!TextUtils.isEmpty(users?.get(position)?.userChoice)){
            holder.itemView.ll_choice.visibility = View.GONE
            holder.itemView.tv_userchoice.visibility = View.VISIBLE
            if(users?.get(position)?.userChoice?.equals("accepted")!!)
            holder.itemView.tv_userchoice.setTextColor(ContextCompat.getColor(context, R.color.green))
            else
            holder.itemView.tv_userchoice.setTextColor(ContextCompat.getColor(context, R.color.red))
        }else{
            holder.itemView.ll_choice.visibility = View.VISIBLE
            holder.itemView.tv_userchoice.visibility = View.GONE
        }
        holder.itemView.btn_accept.setOnClickListener {
            var item = users?.get(position);
            item?.userChoice = "accepted"
            itemClickListener.setClickedInfo(item)
        }
        holder.itemView.btn_reject.setOnClickListener {
            var item = users?.get(position);
            item?.userChoice = "declined"
            itemClickListener.setClickedInfo(item)
        }
    }

    fun refreshAdapter(newUsers: List<ResultUserItem>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mView = view
//        private val tv_name = view.tv_name
//        private val tv_age = view.tv_age
//        private val tv_gender = view.tv_gender
//        private val tv_email = view.tv_email
//        private val tv_contact = view.tv_contact
//        private val image = view.imageView
//        private val tv_userchoice = view.tv_userchoice
        fun bind(usr: ResultUserItem) {
            mView.tv_name.text = usr?.name?.first+" "+usr?.name?.last
            mView.tv_age.text = "${usr?.dob?.age} yrs"
            mView.tv_gender.text = usr?.gender
            mView.tv_email.text = "Email: ${usr?.email}"
            mView.tv_contact.text = usr?.location?.city+", "+usr?.location?.state+", "+usr?.location?.country
            mView.tv_userchoice.text = "You ${usr?.userChoice}"
            Glide.with(context).load(usr?.picture?.large).placeholder(R.drawable.ic_sync)
                .into(mView.imageView)


        }
    }
}