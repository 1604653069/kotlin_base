package com.xlkj.wanandroid.base

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.android.tu.loadingdialog.LoadingDailog
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity


abstract class BaseActivity<P:BasePresenter<*,*>> : RxAppCompatActivity(),BaseView{
    lateinit var mPresenter:P
    lateinit var toast:Toast
    lateinit var loadingDailog:LoadingDailog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noStatusBar()
        setContentView(getLayoutId())
        mPresenter = createPresenter()
        initTipDialog()
        logical()
    }

    private fun initTipDialog() {
        toast = Toast.makeText(this,"",Toast.LENGTH_SHORT)
        loadingDailog = LoadingDailog.Builder(this)
            .setMessage("加载中...")
            .setCancelOutside(true)
            .setCancelable(true)
            .create()
    }

    /**
     * 业务逻辑处理
     */
    abstract fun logical()

    /**
     * 创建presenter
     */
    abstract fun createPresenter(): P

    /**
     * 加载布局
     */
    abstract fun getLayoutId():Int

    /**
     * 显示对话框
     */
    override fun showDialog() = loadingDailog.show()


    /**
     * 隐藏对话框
     */
    override fun dissDialog() = loadingDailog.dismiss()

    /**
     * toast
     */
    fun T(string: String){
        toast?.let {
            it.setText(string)
            it.duration = Toast.LENGTH_SHORT
            it.show()
        }
    }
    private fun noStatusBar(){
        //加入如下代码便可以实现想要的效果
        window.requestFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.apply {
                clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                            or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
                )
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = Color.TRANSPARENT
                navigationBarColor = Color.TRANSPARENT
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }
}