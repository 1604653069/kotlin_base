package com.xlkj.wanandroid.mvp

import android.content.Context
import com.xlkj.wanandroid.base.BasePresenter

class MainPresenter(context: Context, view: MainView) :
    BasePresenter<MainView, MainModel>(context, view) {
    override fun getModel(): MainModel = MainModel()
}