package com.xlkj.wanandroid.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jaeger.library.StatusBarUtil

abstract class BaseActivity<P:BasePresenter<*,*>> : AppCompatActivity(),BaseView{
    lateinit var mPresenter:P
    lateinit var toast:Toast
    lateinit var dialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        StatusBarUtil.setTransparent(this)
        toast = Toast.makeText(this,"",Toast.LENGTH_SHORT)
        dialog = ProgressDialog(this)
        mPresenter = createPresenter()
        logical()
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
    override fun showDialog() =  dialog.show()


    /**
     * 隐藏对话框
     */
    override fun dissDialog() = dialog.dismiss()

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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }
}