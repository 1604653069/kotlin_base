package com.xlkj.wanandroid.http

import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

class RxExceptionUtil {
    companion object{
        fun exceptionHandler(e: Throwable): String? =
            when(e){
                is UnknownHostException ->
                    "网络不可用"
                is SocketTimeoutException ->
                    "请求网络超时"
                is HttpException ->
                    convertStatusCode(e)
                is  ParseException->
                    "数据解析错误"
                is  JSONException->
                    "数据解析错误"
                is ApiException->
                    e.message
                else ->
                    "网络请求失败"
            }
        private fun convertStatusCode(httpException: HttpException): String? =
            when {
                httpException.code() in 500..599 -> {
                    "服务器处理请求出错"
                }
                httpException.code() in 400..499 -> {
                    "服务器无法处理请求"
                }
                httpException.code() in 300..399 -> {
                    "请求被重定向到其他页面"
                }
                else -> {
                    httpException.message()
                }
            }
    }



}