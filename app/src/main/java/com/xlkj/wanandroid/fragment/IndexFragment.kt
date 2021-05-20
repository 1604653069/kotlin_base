package com.xlkj.wanandroid.fragment


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xlkj.wanandroid.R
import com.xlkj.wanandroid.base.BaseFragment
import com.xlkj.wanandroid.model.BannerModel
import com.xlkj.wanandroid.mvp.IndexPresenter
import com.xlkj.wanandroid.mvp.IndexView
import com.zhouwei.mzbanner.holder.MZViewHolder
import kotlinx.android.synthetic.main.fragment_index.*

class IndexFragment: BaseFragment<IndexPresenter>(), IndexView {
    var urls:ArrayList<String> = ArrayList()
    override fun createPresenter(): IndexPresenter = IndexPresenter(ctx,this)

    override fun logical() {
        showDialog()
        mPresenter.getBannerData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_index

    override fun dataBackLisenter(t: List<BannerModel>) {
        for (url in t){
            urls.add(url.imagePath)
        }
        banner.setPages(urls as List<Nothing>?){
              BannerViewHolder()
        }
        banner.duration = 1500
        dissDialog()
    }

    override fun dataFailLisenter(string: String) {
        T(string)
        dissDialog()
    }
    inner class BannerViewHolder:MZViewHolder<String> {
        private lateinit var img:ImageView
        private lateinit var view:View
        override fun onBind(context: Context?, position: Int, data: String?) {
            Glide.with(context!!).load(data).into(img)
        }

        override fun createView(context: Context?): View {
            view = LayoutInflater.from(context).inflate(R.layout.banner_item,null)
            img = view.findViewById(R.id.banner_item_img)
            return view
        }
    }
}