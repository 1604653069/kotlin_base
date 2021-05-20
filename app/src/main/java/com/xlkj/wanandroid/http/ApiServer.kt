package com.xlkj.wanandroid.http

import com.xlkj.wanandroid.base.BaseResponse
import com.xlkj.wanandroid.model.Articles
import com.xlkj.wanandroid.model.BannerModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiServer {

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBannerData(): Observable<BaseResponse<List<BannerModel>>>

    /**
     * 获取首页的文章数据
     */
    @GET("article/list/0/json")
    fun getIndexArticles():Observable<BaseResponse<Articles>>
}