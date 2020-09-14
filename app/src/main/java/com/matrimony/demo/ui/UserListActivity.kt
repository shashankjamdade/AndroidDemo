package com.matrimony.demo.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.matrimony.demo.R
import com.matrimony.demo.listener.DatabaseListener
import com.matrimony.demo.listener.ItemClickListener
import com.matrimony.demo.model.ResultUserItem
import com.matrimony.demo.model.UserListResponse
import com.matrimony.demo.repository.UserRepository
import com.matrimony.demo.util.CommonUtils
import com.matrimony.demo.viewmodel.UserListViewModel
import com.matrimony.demo.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject


class UserListActivity : DaggerAppCompatActivity(),
    ItemClickListener, DatabaseListener {

    private var userListView: View? = null
    lateinit var viewModel: UserListViewModel
    private var userListAdapter: UserListAdapter? = null
    private var clickedId: Int? = -1
    var mContainerId: Int = -1
//    var database: AppDatabase? = null

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        initViewModel()
        initAdapter()

        if (CommonUtils.isOnline(this)) {
            observeViewModel()
            viewModel.fetchUserListInfo(10)
        } else {
            CommonUtils.createSnackBar( findViewById(android.R.id.content), "No network !")
            val mObservableProduct: LiveData<List<ResultUserItem>>? =
                userRepository.getAllUsers();
            mObservableProduct?.observe(this, object : Observer<List<ResultUserItem>> {
                override fun onChanged(@Nullable itemlist: List<ResultUserItem>?) {
                    CommonUtils.printLog("DATARETR----> ", "${Gson().toJson(itemlist)}")
                    if(itemlist!=null){
                        userListAdapter?.refreshAdapter(itemlist!!)
                    }else{
                        CommonUtils.createSnackBar( findViewById(android.R.id.content), "No data found, please on wifi/mobile data !")
                    }
                }
            })
        }
    }


    private fun initViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewmodelProviderFactory).get(UserListViewModel::class.java)
    }

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

    private fun observeViewModel() {

        viewModel.fetchUsersLiveData().observe(this, Observer {
            loading_progress.visibility = View.VISIBLE
            it?.let {
                val mObservableProduct: LiveData<List<ResultUserItem>>? =
                    userRepository.getAllUsers();
                mObservableProduct?.observe(this, object : Observer<List<ResultUserItem>> {
                    override fun onChanged(@Nullable itemlist: List<ResultUserItem>?) {
                        loading_progress.visibility = View.GONE
                        CommonUtils.printLog("DATARETR----> ", "${Gson().toJson(itemlist)}")
                        if(itemlist!=null){
                            userListAdapter?.refreshAdapter(itemlist!!)
                        }
                    }
                })
//                userListAdapter?.refreshAdapter(it?.results!!)
            }
        })

        viewModel.fetchError().observe(this, Observer {
            it?.let {
                if (!TextUtils.isEmpty(it)) {
                    Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
                }

            }
        })
    }


    override fun setClickedInfo(data: Any?) {
        if(data!=null && data is ResultUserItem){
            viewModel?.updateUserInfo(data)
        }
//        clickedId = data.id
//        launchDetailFragment()
    }

    override fun getUpdatedData(obj: Any?, apiName: String) {
        if (apiName.equals("userlist")) {
            if (obj != null && obj is UserListResponse) {
                userListAdapter?.refreshAdapter(obj?.results!!)
            }
        }
    }

    override fun IsDataInserted(apiName: String) {
//        DBOperations.getUserResponseFromDb(
//            database,
//            this,
//            this,
//           "userlist"
//        )
    }

}