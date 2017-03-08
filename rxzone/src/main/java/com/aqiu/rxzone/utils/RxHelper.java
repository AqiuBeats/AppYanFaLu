package com.aqiu.rxzone.utils;


import com.aqiu.rxzone.ui.base.RxActivity;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * rxRecycle的封装
 * Created by aqiu on 2017/3/6.
 */
public abstract class RxHelper<T> {
    public Observable.Transformer<T, T> io_main(final RxActivity context) {
        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                @SuppressWarnings("unchecked")
                Observable<T> observable = (Observable<T>) tObservable
                        .compose(RxSchedulersHelper.<T>io_main())//子线程运行，主线程回调
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                doMain();
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycle.bindUntilEvent(context.lifecycle(), ActivityEvent.STOP));//取消Rxjava,当页面不显示时,取消连网操作,亲测可用
                return observable;
            }
        };
    }

    /**
     * 主线程初始操作
     */
    public abstract void doMain();
}
