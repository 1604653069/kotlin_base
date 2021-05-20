package com.xlkj.wanandroid.rxjava

import android.content.Context
import com.xlkj.wanandroid.base.BaseResponse
import com.xlkj.wanandroid.http.RxExceptionUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class RxSubscribe<T>(val context: Context): Observer<BaseResponse<T>> {
    lateinit var disposable:Disposable

    override fun onComplete() {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d

    }

    override fun onNext(t: BaseResponse<T>) = if(t.errorCode==0) onSuccess(t) else onFail(t.errorMsg)

    override fun onError(e: Throwable) {
        onFail(RxExceptionUtil.exceptionHandler(e)!!)
        if(disposable!=null&&!disposable.isDisposed){
            disposable.dispose()
        }
    }
    abstract fun onSuccess(t: BaseResponse<T>)

    abstract fun onFail(string:String)
}