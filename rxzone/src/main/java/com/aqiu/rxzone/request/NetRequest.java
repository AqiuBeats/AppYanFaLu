package com.aqiu.rxzone.request;

import com.aqiu.rxzone.bean.Douban;
import com.aqiu.rxzone.http.RetrofitClient;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求
 * Created by aqiu on 2017/3/2.
 */

public class NetRequest {
    /**
     * 1.登录请求
     */
    public static Observable<Douban> getObservable(String start, String count) {
        GetDetailService getDetailService = RetrofitClient.getInstance().create(GetDetailService
                .class);
        return getDetailService.getLoginDetail(start, count);
    }

    private interface GetDetailService {
        @GET("v2/movie/top250")
        Observable<Douban> getLoginDetail(@Query("start") String start, @Query("count") String count);
    }
}
