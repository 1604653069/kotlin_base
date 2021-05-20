package com.xlkj.wanandroid.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment<P:BasePresenter<*,*>>:Fragment(),BaseView{
    lateinit var mPresenter:P
    lateinit var toast: Toast
    lateinit var ctx:Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mPresenter = createPresenter()
        toast = Toast.makeText(context,"",Toast.LENGTH_SHORT)
        logical()
        return inflater.inflate(getLayoutId(),container,false)
    }


    abstract fun createPresenter(): P

    override fun showDialog() {

    }

    override fun dissDialog() {

    }

    abstract fun logical()

    abstract fun getLayoutId():Int

    fun T(string:String){
        toast?.let {
            toast.setText(string)
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }
}