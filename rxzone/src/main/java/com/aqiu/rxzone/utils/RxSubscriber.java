package com.aqiu.rxzone.utils;

import com.aqiu.rxzone.application.MyApplication;

import rx.Subscriber;

/**
 * 对三种状态的封装
 * Created by a on 2016/5/6.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
//        ProgressDialogUtil.dismiss();
        Tutils.showToast(MyApplication.getObjectContext(), "连接成功");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

//        _onError(e.getMessage());
//        ProgressDialogUtil.dismiss();
        Tutils.showToast(MyApplication.getObjectContext(), "连接失败");
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

//    public abstract void _onError(String msg);
}
