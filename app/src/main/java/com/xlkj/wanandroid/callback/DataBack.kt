package com.xlkj.wanandroid.callback

interface DataBack<T> {
    fun dataBackLisenter(t:T)

    fun dataFailLisenter(string: String)
}