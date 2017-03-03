package com.aqiu.rxzone.utils;

import rx.Subscriber;

/**
 * Created by a on 2016/5/6.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        ProgressDialogUtil.dismiss();
        L.i("即使我Activity不在了,我也不会立马消失,哈哈哈!");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        _onError(e.getMessage());
        ProgressDialogUtil.dismiss();
//        if (!NetUtils.isConnected(MyApplication.getContextObject())) {
//            MYToast.show("请求失败，请检查网络!");
//            return;
//        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    public abstract void _onNext(T t);

    public abstract void _onError(String msg);
}
