package com.xlkj.wanandroid.rxjava

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.trello.rxlifecycle3.android.lifecycle.kotlin.bindToLifecycle
import com.trello.rxlifecycle3.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * @description: 线程调度封装
 * @author : yzq
 * @date   : 2018/7/9
 * @time   : 15:19
 *
 */

class RxSchedulers {

    companion object {
        fun <T> io2main(lifecycleOwner: LifecycleOwner): ObservableTransformer<T, T> {
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .bindUntilEvent(lifecycleOwner,Lifecycle.Event.ON_DESTROY)
                    .bindToLifecycle(lifecycleOwner)
                    .debounce(1,TimeUnit.SECONDS)
            }
        }

    }
}
