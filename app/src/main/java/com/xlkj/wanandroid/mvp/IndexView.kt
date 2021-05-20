package com.xlkj.wanandroid.mvp

import com.xlkj.wanandroid.base.BaseView
import com.xlkj.wanandroid.callback.DataBack
import com.xlkj.wanandroid.model.BannerModel

interface IndexView:BaseView,DataBack<List<BannerModel>> {

}