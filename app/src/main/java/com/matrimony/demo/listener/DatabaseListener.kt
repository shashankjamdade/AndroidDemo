package com.matrimony.demo.listener

interface DatabaseListener {

    fun getUpdatedData(obj:Any?, apiName:String)

    fun IsDataInserted(apiName:String)

}