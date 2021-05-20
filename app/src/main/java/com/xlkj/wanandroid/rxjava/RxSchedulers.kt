package com.xlkj.wanandroid.rxjava

import android.content.Context
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.RxActivity
import com.trello.rxlifecycle3.components.RxFragment
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.trello.rxlifecycle3.components.support.RxFragmentActivity
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxSchedulers {
    
    companion object {
        fun <T> io2main(): ObservableTransformer<T, T> {
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> observableIO2Main(context: Context?): ObservableTransformer<T, T>? {
            return ObservableTransformer { upstream: Observable<T> ->
                val observable = upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                this!!.composeContext(context!!, observable)!!
            }
        }

        fun <T> observableIO2Main(fragment: RxFragment): ObservableTransformer<T, T?>? {
            return ObservableTransformer { upstream: Observable<T> ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(fragment.bindToLifecycle())
            }
        }
        fun <T> composeContext(
            context: Context,
            observable: Observable<T>
        ): ObservableSource<T>? {
            return when (context) {
                is RxActivity -> {
                    observable.compose((context as RxActivity).bindUntilEvent(ActivityEvent.DESTROY))
                }
                is RxFragmentActivity -> {
                    observable.compose(
                        (context as RxFragmentActivity).bindUntilEvent(
                            ActivityEvent.DESTROY
                        )
                    )
                }
                is RxAppCompatActivity -> {
                    observable.compose(
                        (context as RxAppCompatActivity).bindUntilEvent(
                            ActivityEvent.DESTROY
                        )
                    )
                }
                else -> {
                    observable
                }
            }
        }
    }
}
