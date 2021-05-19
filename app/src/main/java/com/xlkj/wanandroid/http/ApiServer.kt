package com.xlkj.wanandroid.http

import com.xlkj.wanandroid.base.BaseResponse
import com.xlkj.wanandroid.model.BannerModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiServer {

    @GET("banner/json")
    fun getBannerData(): Observable<BaseResponse<List<BannerModel>>>
}