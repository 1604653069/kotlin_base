package com.xlkj.wanandroid.mvp

import android.content.Context
import com.xlkj.wanandroid.base.BaseModel
import com.xlkj.wanandroid.base.BaseResponse
import com.xlkj.wanandroid.callback.DataBack
import com.xlkj.wanandroid.http.RetrofitManager
import com.xlkj.wanandroid.model.Articles
import com.xlkj.wanandroid.model.BannerModel
import com.xlkj.wanandroid.rxjava.RxSchedulers
import com.xlkj.wanandroid.rxjava.RxSubscribe

class IndexModel(val context: Context): BaseModel(context) {

    fun getBannerData(dataBack: DataBack<List<BannerModel>>){
        RetrofitManager.service
            .getBannerData()
            .compose(RxSchedulers.observableIO2Main(context))
            .subscribe(object :RxSubscribe<List<BannerModel>>(context){
                override fun onFail(string: String) {
                    dataBack.dataFailLisenter(string)
                }
                override fun onSuccess(t: BaseResponse<List<BannerModel>>) {
                    dataBack.dataBackLisenter(t.data)
                }
            })
    }
    fun getArticles(dataBack:DataBackLisenter){
        RetrofitManager.service
            .getIndexArticles()
            .compose(RxSchedulers.observableIO2Main(context))
            .subscribe(object :RxSubscribe<Articles>(context){
                override fun onSuccess(t: BaseResponse<Articles>) {
                    dataBack.onDataBackSuccess(t.data)
                }
                override fun onFail(string: String) {
                    dataBack.onDataBackFail(string)
                }
            })
    }
    interface DataBackLisenter{
        fun onDataBackSuccess(data:Articles)
        fun onDataBackFail(string: String)
    }
}