package com.xlkj.wanandroid.fragment


import com.xlkj.wanandroid.R
import com.xlkj.wanandroid.base.BaseFragment
import com.xlkj.wanandroid.mvp.IndexPresenter
import com.xlkj.wanandroid.mvp.IndexView

class IndexFragment: BaseFragment<IndexPresenter>(), IndexView {

    override fun createPresenter(): IndexPresenter = IndexPresenter(context!!,this)

    override fun logical() {

    }

    override fun getLayoutId(): Int = R.layout.fragment_index

}