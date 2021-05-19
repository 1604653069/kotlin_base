package com.xlkj.wanandroid.base

import android.content.Context

abstract class BasePresenter<V:BaseView,M:BaseModel>(context: Context,view:V) {
    var mView:V?= view
    val mContext:Context = context
    val mModel:M = getModel()

    abstract fun getModel(): M

    fun detachView(){
        mView = null
    }
}