package com.xlkj.wanandroid.http

class ApiException(private val mErrorCode: Int, errorMessage: String?) :
    RuntimeException(errorMessage)