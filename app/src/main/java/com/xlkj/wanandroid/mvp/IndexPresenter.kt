package com.xlkj.wanandroid.mvp

import android.content.Context
import com.xlkj.wanandroid.base.BasePresenter
import com.xlkj.wanandroid.callback.DataBack
import com.xlkj.wanandroid.model.Articles
import com.xlkj.wanandroid.model.BannerModel

class IndexPresenter(context: Context, view:IndexView) : BasePresenter<IndexView, IndexModel>(context, view),
    DataBack<List<BannerModel>>, IndexModel.DataBackLisenter {
    override fun getModel(): IndexModel = IndexModel(mContext)

    fun getBannerData(){
        mModel.getBannerData(this)
    }
    fun getIndexArticles(){
        mModel.getArticles(this)
    }

    override fun dataBackLisenter(t: List<BannerModel>) {
        mView?.dataBackLisenter(t)
    }

    override fun dataFailLisenter(string: String) {
        mView?.dataFailLisenter(string)
    }

    override fun onDataBackSuccess(data: Articles) {
        mView?.onDataBackSuccess(data)
    }

    override fun onDataBackFail(string: String) {
        mView?.onDataBackFail(string)
    }
}