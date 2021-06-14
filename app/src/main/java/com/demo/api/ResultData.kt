package com.demo.api

data class ResultData<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResultData<T> {
            return ResultData(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResultData<T> {
            return ResultData(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResultData<T> {
            return ResultData(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
