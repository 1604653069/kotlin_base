package com.xlkj.wanandroid.rxjava

import android.content.Context
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class RxSubscribe<T>(val context: Context): Observer<T> {
    lateinit var disposable:Disposable
    override fun onComplete() {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d

    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFail(e.toString())
        if(disposable!=null&&!disposable.isDisposed){
            disposable.dispose()
        }
    }
    abstract fun onSuccess(t: T)

    abstract fun onFail(string:String)
}