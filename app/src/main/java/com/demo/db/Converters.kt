package com.demo.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.demo.model.ResultUserItem


class Converters {

//    UserList Response
    @TypeConverter // To convert DB dataObject into JSON string and insert in DB
    fun fromMapUserResponse(list: ArrayList<ResultUserItem>): String {
        val listType = object : TypeToken<ArrayList<ResultUserItem>>() {}.type
        return Gson().toJson(list, listType)
    }

    @TypeConverter // To convert JSON string into dataObject and fetch the data
    fun toMapUserResponse(value: String): ArrayList<ResultUserItem>? {
        val listType = object : TypeToken<ArrayList<ResultUserItem>>() {}.type
        return Gson().fromJson<ArrayList<ResultUserItem>>(value, listType)
    }

}
