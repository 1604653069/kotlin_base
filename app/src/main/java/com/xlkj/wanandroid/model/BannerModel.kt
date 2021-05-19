package com.xlkj.wanandroid.model

class BannerModel(
    val desc:String,
    val id:Int,
    val imagePath:String,
    val isVisible:Int,
    val order:Int,
    val title:String,
    val type:Int,
    val url:String
){
    override fun toString(): String {
        return "BannerModel(desc='$desc', id=$id, imagePath='$imagePath', isVisible=$isVisible, order=$order, title='$title', type=$type, url='$url')"
    }
}