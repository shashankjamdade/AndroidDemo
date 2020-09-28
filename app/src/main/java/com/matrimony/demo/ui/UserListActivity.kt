package com.matrimony.demo.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.matrimony.demo.R
import com.matrimony.demo.listener.ItemClickListener
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.util.CommonUtils
import com.matrimony.demo.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*

@AndroidEntryPoint
class UserListActivity : AppCompatActivity(), ItemClickListener {

    private var userListAdapter: UserListAdapter? = null
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        initAdapter()
        fetchUserList();
    }


    /**
     * Initialize adapter
     */
    private fun initAdapter() {
        userListAdapter = UserListAdapter(
            arrayListOf(),
            this@UserListActivity,
            this
        )
        userListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
    }

    /**
     * Fetch user list from API or localDb
     */
    private fun fetchUserList() {
        if (CommonUtils.isOnline(this)) {
            //From API
            loading_progress.visibility = View.VISIBLE
            viewModel.fetchUserListInfo(10).observe(this, Observer {
                it?.let {
                    loading_progress.visibility = View.GONE
                    if(it?.results!=null && it?.results?.size!!>0){
                        //To attach observer on livedata of Room fetching data from DB itself
                        fetchUsersFromDb()
                    }else{
                        CommonUtils.createSnackBar( findViewById(android.R.id.content), resources?.getString(R.string.no_net)!!)
                    }
                }
            })
        }else{
            //From local DB
            fetchUsersFromDb()
        }
        /**
         * If any error occured in viewmodel while fetching data will show/handle
         */
        viewModel.fetchError().observe(this, Observer {
            it?.let {
                if (!TextUtils.isEmpty(it)) {
                    CommonUtils.createSnackBar(findViewById(android.R.id.content), "$it")
                }

            }
        })
    }

    fun fetchUsersFromDb(){
        //From local DB
        viewModel?.fetchUsersFromDb()?.observe(this, object : Observer<List<ResultUserItem>> {
            override fun onChanged(@Nullable itemlist: List<ResultUserItem>?) {
                loading_progress.visibility = View.GONE
                CommonUtils.printLog("DATARETR----> ", "${Gson().toJson(itemlist)}")
                if(itemlist!=null && itemlist?.size>0){
                    userListAdapter?.refreshAdapter(itemlist!!)
                }else{
                    CommonUtils.createSnackBar( findViewById(android.R.id.content), resources?.getString(R.string.no_data)!!)
                }
            }
        })
    }

    /**
     * Will call on click of the buttons of list
     */
    override fun setClickedInfo(data: Any?) {
        if(data!=null && data is ResultUserItem){
            viewModel?.updateUserInfo(data)
        }
    }

}