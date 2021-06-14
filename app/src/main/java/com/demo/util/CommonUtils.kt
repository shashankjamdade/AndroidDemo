package com.demo.util;

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.matrimony.demo.BuildConfig

class  CommonUtils{

    companion object{
        var toast: Toast? = null;
        @SuppressLint("WrongConstant")
        fun createSnackBar(view: View, message: String) {
            hideSoftKeyboard(view)
//            ColoredSnackbar.info(Snackbar.make(view, message, MESSAGE_DURATION)).show()

            if (!message.equals("")) {
                toast?.run { this.cancel() }
                toast = Toast.makeText(view.context, message, Toast.LENGTH_LONG)
                toast?.run { this.show() }
            }
        }

        fun showSoftKeyboard(context: Activity) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }

        fun hideSoftKeyboard(view: View) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun printLog(tag: String, message: String) {
            if (BuildConfig.DEBUG) {
                Log.e(tag, message)
            }
        }

        fun loadImageWithGlide(context: Context, urlLink:String, imageView: ImageView){
            urlLink?.run {
                Glide.with(context)
                    .load(urlLink)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(R.color.)
//                .placeholder(R.color.gray)
                    .into(imageView)
            }
        }

        fun isOnline(context: Context): Boolean {
            val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connMgr?.activeNetworkInfo
            return networkInfo?.isConnected == true
        }


    }
}