package com.xlkj.wanandroid.fragment


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.xlkj.wanandroid.R
import com.xlkj.wanandroid.base.BaseFragment
import com.xlkj.wanandroid.model.Articles
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
        mPresenter.getIndexArticles()
    }

    override fun getLayoutId(): Int = R.layout.fragment_index

    override fun onDataBackSuccess(data: Articles) {
        Log.i("TAG","接收到了首页的数据:$data")
    }

    override fun onDataBackFail(string: String) {

    }

    override fun dataBackLisenter(t: List<BannerModel>) {
        for (url in t){
            urls.add(url.imagePath)
        }
        banner.setPages(t as List<Nothing>?){
              BannerViewHolder()
        }
        banner.duration = 1500
        dissDialog()
    }

    override fun dataFailLisenter(string: String) {
        T(string)
        dissDialog()
    }
    inner class BannerViewHolder:MZViewHolder<BannerModel> {
        private lateinit var img:ImageView
        private lateinit var view:View
        private lateinit var content:TextView
        override fun onBind(context: Context?, position: Int, data: BannerModel?) {
            Glide.with(context!!).load(data?.imagePath).into(img)
            content.text = data?.title
        }

        override fun createView(context: Context?): View {
            view = LayoutInflater.from(context).inflate(R.layout.banner_item,null)
            img = view.findViewById(R.id.banner_item_img)
            content = view.findViewById(R.id.banner_item_tv)
            return view
        }
    }
}