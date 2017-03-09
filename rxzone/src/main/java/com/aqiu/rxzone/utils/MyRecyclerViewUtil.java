package com.aqiu.rxzone.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.TypedValue;

import com.aqiu.rxzone.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * 一套解决下拉刷新和上拉加载更多的方案
 * Created by aqiu on 2017/3/6.
 */

public class MyRecyclerViewUtil {
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RefreshDataListener mRefreshDataListener;
    private OnScrolledLinstener mOnScrolledLinstener;
    private ImageAutoLoadScrollListener imageAutoLoadScrollListener;

    /**
     * (1)引入BaseRecyclerViewAdapterHelper库,减少Adapter编写重复代码量
     * (2)引入官方SwipeRefreshLayout下拉刷新组件
     * (3)通过RecycleView滑动状态,控制图片是否联网,尽可能的减少卡顿
     */
    public MyRecyclerViewUtil() {
        imageAutoLoadScrollListener=new ImageAutoLoadScrollListener();
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param swipeRefreshLayout
     * @param recyclerView
     * @param manager
     * @param refreshDataListener
     */
    public void init(final Context context, final SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, LayoutManager manager,
                     RefreshDataListener refreshDataListener, OnScrolledLinstener mOnScrolledLinstener) {
        initConfig(context, swipeRefreshLayout, recyclerView, refreshDataListener, mOnScrolledLinstener);
        initRefreshLayout();
        initRecy(manager, imageAutoLoadScrollListener);
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param swipeRefreshLayout
     * @param recyclerView
     * @param refreshDataListener
     */
    private void initConfig(Context context, SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, RefreshDataListener refreshDataListener, OnScrolledLinstener mOnScrolledLinstener) {
        this.mContext = context;
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mRecyclerView = recyclerView;
        this.mRefreshDataListener = refreshDataListener;
        this.mOnScrolledLinstener = mOnScrolledLinstener;
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
     * 在init()方法执行之后执行
     * 初始化上拉加载
     */
    public void initAdapter(Context context,BaseQuickAdapter baseQuickAdapter) {
        /**
         * 自定义上拉显示方案,可自定义
         */
//        @SuppressLint("InflateParams")
//        View view = LayoutInflater.from(context).inflate(R.layout.recy_foot, null);//自定义底部显示加载view
//        TextView tv_foot_msg = (TextView) view.findViewById(R.id.id_tv_loadingmsg);
//        tv_foot_msg.setText("数据加载中....");
//        baseQuickAdapter.setLoadingView(view);
        baseQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);//设置显示动画
        baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
    private void initRecy(LayoutManager manager, ImageAutoLoadScrollListener imageAutoLoadScrollListener) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//可自定义显示动画
        mRecyclerView.setLayoutManager(manager);//定义RecycleView显示的布局
        mRecyclerView.addOnScrollListener(imageAutoLoadScrollListener);
    }

    /**
     * 要使用必须在init初始化之后调用之后调用
     * 停止显示下拉图示
     */
    public void endRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private class ImageAutoLoadScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //暂只支持竖方向的滑动
            if (dy != 0) {
                mOnScrolledLinstener.onScrollStateScrolling();
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case SCROLL_STATE_IDLE: // 是当屏幕停止滚动时,表示当前并不处于滑动状态
                    //对于滚动不加载图片的尝试
                    mOnScrolledLinstener.onScrollStateStoping();
                    break;
                case SCROLL_STATE_DRAGGING: // 表示当前RecyclerView处于滑动状态（手指在屏幕上）
                    mOnScrolledLinstener.onScrollStateScrolling();
                    break;
                case SCROLL_STATE_SETTLING: //表示当前RecyclerView处于滑动状态，（手已经离开屏幕）
                    mOnScrolledLinstener.onScrollStateScrolling();
                    break;
            }
        }
    }

    public interface OnScrolledLinstener {
        /**
         * RecycleView停止滑动触发事件
         */
        void onScrollStateStoping();

        /**
         * RecycleView滑动中触发事件
         */
        void onScrollStateScrolling();

    }

    /**
     * 对外提供下拉刷新和上拉加载的接口
     */
    public interface RefreshDataListener {
        /**
         * 下拉刷新触发事件
         */
        void onRefresh();

        /**
         * 上拉加载触发事件
         */
        void loadMore();
    }
}
