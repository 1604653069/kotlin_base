package com.xlkj.wanandroid

import android.graphics.Color
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.jaeger.library.StatusBarUtil
import com.xlkj.wanandroid.base.BaseActivity
import com.xlkj.wanandroid.base.BaseResponse
import com.xlkj.wanandroid.http.ApiServer
import com.xlkj.wanandroid.http.RetrofitManager
import com.xlkj.wanandroid.model.BannerModel
import com.xlkj.wanandroid.mvp.MainPresenter
import com.xlkj.wanandroid.mvp.MainView
import com.xlkj.wanandroid.rxjava.RxSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity:BaseActivity<MainPresenter>(), MainView {


    override fun createPresenter(): MainPresenter = MainPresenter(this,this)



    override fun getLayoutId(): Int = R.layout.activity_main


    override fun logical() {
        bottom_navigation?.let {
            it.addItem(BottomNavigationItem(R.drawable.index,"首页"))
            it.addItem(BottomNavigationItem(R.drawable.progress,"项目"))
            it.addItem(BottomNavigationItem(R.drawable.tixi,"体系"))
            it.addItem(BottomNavigationItem(R.drawable.daohang,"导航"))
            it.addItem(BottomNavigationItem(R.drawable.wujiaoxing,"公众号"))
            it.setMode(MODE_FIXED)
            it.backgroundColor = Color.TRANSPARENT
            it.inActiveColor = R.color.bottom_color
            it.activeColor = R.color.bottom_sel_color
            it.initialise()
        }
         RetrofitManager.create(ApiServer::class.java)
             .getBannerData()
             .compose(RxSchedulers.io2main(this))
             .subscribe {

             }
    }
}