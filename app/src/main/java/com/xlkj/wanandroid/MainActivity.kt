package com.xlkj.wanandroid

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.xlkj.wanandroid.base.BaseActivity
import com.xlkj.wanandroid.fragment.IndexFragment
import com.xlkj.wanandroid.mvp.MainPresenter
import com.xlkj.wanandroid.mvp.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:BaseActivity<MainPresenter>(), MainView,
    BottomNavigationBar.OnTabSelectedListener {

    lateinit var fragments:ArrayList<Fragment>

    override fun createPresenter(): MainPresenter = MainPresenter(this,this)


    override fun getLayoutId(): Int = R.layout.activity_main


    override fun logical() {
        fragments = ArrayList()
        fragments.add(IndexFragment())
        bottom_navigation?.let {
            it.apply {
                addItem(BottomNavigationItem(R.drawable.index,"首页"))
                addItem(BottomNavigationItem(R.drawable.progress,"项目"))
                addItem(BottomNavigationItem(R.drawable.tixi,"体系"))
                addItem(BottomNavigationItem(R.drawable.daohang,"导航"))
                addItem(BottomNavigationItem(R.drawable.wujiaoxing,"公众号"))
                setMode(MODE_FIXED)
                setFirstSelectedPosition(0)
                backgroundColor = Color.TRANSPARENT
                inActiveColor = R.color.bottom_color
                activeColor = R.color.bottom_sel_color
                initialise()
            }
        }
        bottom_navigation.setTabSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.frame,fragments[0]).commit()
    }

    override fun onTabReselected(position: Int) {

    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabSelected(position: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.frame,fragments[position]).commit()
    }
}