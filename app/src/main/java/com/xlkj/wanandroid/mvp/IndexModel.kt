package com.xlkj.wanandroid.mvp

import android.content.Context
import com.xlkj.wanandroid.base.BaseModel
import com.xlkj.wanandroid.base.BaseResponse
import com.xlkj.wanandroid.callback.DataBack
import com.xlkj.wanandroid.http.RetrofitManager
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
}