package com.xlkj.wanandroid.base

class BaseResponse<T>(val errorCode:Int,val errorMsg:String,val data:T) {
    override fun toString(): String {
        return "BaseResponse(errorCode=$errorCode, errorMsg='$errorMsg', data=$data)"
    }
}