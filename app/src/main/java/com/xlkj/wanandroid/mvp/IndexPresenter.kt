package com.xlkj.wanandroid.mvp

import android.content.Context
import com.xlkj.wanandroid.base.BasePresenter

class IndexPresenter(context: Context, view:IndexView) : BasePresenter<IndexView, IndexModel>(context, view) {
    override fun getModel(): IndexModel = IndexModel()
}