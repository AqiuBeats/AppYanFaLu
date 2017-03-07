package com.aqiu.rxzone.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aqiu.rxzone.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 一套解决下拉刷新和上拉加载更多的方案
 * Created by aqiu on 2017/3/6.
 */

public class MyRecyclerViewUtil {
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter mQuickAdapter;
    private RefreshDataListener mRefreshDataListener;

    /**
     * (1)引入BaseRecyclerViewAdapterHelper库,减少Adapter编写重复代码量
     * (2)引入官方SwipeRefreshLayout下拉刷新组件
     * (3)通过RecycleView滑动状态,控制图片是否联网,尽可能的减少卡顿
     */
    public MyRecyclerViewUtil() {

    }

    /**
     * 初始化数据
     *测试Github
     * @param context
     * @param swipeRefreshLayout
     * @param recyclerView
     * @param adapter
     * @param refreshDataListener
     */
    public void init(final Context context, final SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, BaseQuickAdapter adapter, RefreshDataListener refreshDataListener) {
        initConfig(context, swipeRefreshLayout, recyclerView, adapter, refreshDataListener);
        initRefreshLayout();
        initAdapter();
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param swipeRefreshLayout
     * @param recyclerView
     * @param adapter
     * @param refreshDataListener
     */
    private void initConfig(Context context, SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, BaseQuickAdapter adapter, RefreshDataListener refreshDataListener) {
        this.mContext = context;
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mRecyclerView = recyclerView;
        this.mQuickAdapter = adapter;
        this.mRefreshDataListener = refreshDataListener;
    }

    /**
     * 初始化SwipeRefreshLayout
     */
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.red, R.color.gold, R.color.green);//设置进度动画的颜色。
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);//设置进度圈大小
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);//设置进度圈背景色
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, mContext.getResources().getDisplayMetrics()));//设置进度圈休息的位置
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshDataListener.onRefresh();
            }
        });
    }

    /**
     * 初始化上拉加载
     */
    private void initAdapter() {
        /**
         * 自定义上拉显示方案,可自定义
         */
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(mContext).inflate(R.layout.recy_foot, null);//自定义底部显示加载view
        TextView tv_foot_msg = (TextView) view.findViewById(R.id.id_tv_loadingmsg);
        tv_foot_msg.setText("数据加载中....");
        mQuickAdapter.setLoadingView(view);
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);//设置显示动画
        mQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRefreshDataListener.loadMore();
            }
        });
    }

    /**
     * 初始化RecycleView的动画和布局配置
     *
     * @param manager
     */
    private void initRecy(LayoutManager manager) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//可自定义显示动画
        mRecyclerView.setLayoutManager(manager);//定义RecycleView显示的布局
    }

    /**
     * 要使用必须在init初始化之后调用之后调用
     * 停止显示下拉图示
     */
    public void endRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 对外提供下拉刷新和上拉加载的接口
     */
    interface RefreshDataListener {
        void onRefresh();//下拉刷新配套动作

        void loadMore();//上拉加载配套动作
    }
}
