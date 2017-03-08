package com.aqiu.rxzone.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.adapter.GirlsAdapter;
import com.aqiu.rxzone.bean.Girls;
import com.aqiu.rxzone.request.NetRequest;
import com.aqiu.rxzone.ui.base.BaseActivity;
import com.aqiu.rxzone.utils.L;
import com.aqiu.rxzone.utils.RxHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class TestActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView_test)
    RecyclerView mRecy;
    @BindView(R.id.SwipeRefreshLayout_test)
    SwipeRefreshLayout mSwipeRefreshWidget;

    private GirlsAdapter mQuickAdapter;
    private int pager;
    private boolean isOnRefresh;
    private boolean isOnLoadMore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int initLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initDatas() {
        pager = 1;
        isOnRefresh = false;
        isOnLoadMore = false;
        mSwipeRefreshWidget.setColorSchemeResources(R.color.blue, R.color.red, R.color.gold, R.color.green);//设置进度动画的颜色。
        mSwipeRefreshWidget.setSize(SwipeRefreshLayout.DEFAULT);//设置进度圈大小11
        mSwipeRefreshWidget.setProgressBackgroundColorSchemeResource(R.color.white);//设置进度圈背景色
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));//设置进度圈休息的位置
        mSwipeRefreshWidget.setOnRefreshListener(this);//设置进度圈下拉响应
        //关于RecycleView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(TestActivity.this, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecy.setHasFixedSize(true);
        mRecy.setItemAnimator(new DefaultItemAnimator());
        mRecy.setLayoutManager(gridLayoutManager);
        netRequest(pager);
    }

    @Override
    public void reDatas() {

    }


    private void initAdapter(List<Girls.TngouEntity> girls) {
        mQuickAdapter = new GirlsAdapter(girls);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(TestActivity.this).inflate(R.layout.recy_foot, null);//自定义底部显示加载view
        //        View emptyView = LayoutInflater.from(_mActivity).inflate(R.layout.recy_empty, null);//自定义空布局
        TextView tv_foot_msg = (TextView) view.findViewById(R.id.id_tv_loadingmsg);
        //        pb_foot = (ProgressBar) view.findViewById(pb_foot);
        tv_foot_msg.setText("数据加载中....");
        mQuickAdapter.setLoadingView(view);
        //        mQuickAdapter.setEmptyView(emptyView);
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);//设置显示动画
        mQuickAdapter.openLoadMore(PAGE_SIZE);
        //        mQuickAdapter.setA
        mQuickAdapter.setOnLoadMoreListener(this);
        mRecy.setAdapter(mQuickAdapter);
    }

    private void netRequest(int i) {
        NetRequest.getGirlsObservable(i, 10)
                .compose(new RxHelper<Girls>() {
                    @Override
                    public void doMain() {
                        //                        if (SwipeRefreshLayout != null) {
                        //                            SwipeRefreshLayout.setEnabled(true);
                        //                        }
                    }
                }.io_main(TestActivity.this))
                .subscribe(new Subscriber<Girls>() {
                    @Override
                    public void onCompleted() {
                        if (isOnRefresh) {
                            mSwipeRefreshWidget.setRefreshing(false);
                            isOnRefresh = false;
                        }

                        //                        if (isOnLoadMore) {
                        //                            //                            girlsAdapter.loadComplete();
                        //                            L.e("上拉完成_正确");
                        //                            isOnLoadMore = false;
                        //                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isOnRefresh) {
                            mSwipeRefreshWidget.setRefreshing(false);
                            isOnRefresh = false;
                        }
                        L.e("上拉完成_错误");
                        mQuickAdapter.loadComplete();
                        //                        if (isOnLoadMore) {
                        //                            L.e("上拉完成_错误");
                        //                            isOnLoadMore = false;
                        //                        }
                    }

                    @Override
                    public void onNext(Girls girls) {
                        if (girls != null) {
                            final List<Girls.TngouEntity> girlsTngou = girls.getTngou();
                            synchronized (this) {
                                mRecy.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isOnLoadMore) {
                                            L.e("上拉加载");
                                            mQuickAdapter.addData(girlsTngou);
                                            boolean loading = mQuickAdapter.isLoading();
                                            L.e(loading + "==家在了么");
                                            //                                            mQuickAdapter.loadComplete();
                                        } else {
                                            initAdapter(girlsTngou);
                                        }
                                    }
                                }, 500);
                            }
                        } else {
                            L.e("上拉完成_错误");
                        }
                    }
                });
    }

    /**
     * 下拉刷新触发事件
     */
    @Override
    public void onRefresh() {
        synchronized (this) {
            pager = 1;
            isOnRefresh = true;
            netRequest(pager);
        }
    }

    /**
     * 上拉加载触发事件
     */
    @Override
    public void onLoadMoreRequested() {
        L.e("我触发了");
        synchronized (this) {
            isOnLoadMore = true;
            L.e(pager + "");
            netRequest(pager++);
        }
    }
}
