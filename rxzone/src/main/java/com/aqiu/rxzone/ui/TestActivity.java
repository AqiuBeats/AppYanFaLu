package com.aqiu.rxzone.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.adapter.GirlsAdapter;
import com.aqiu.rxzone.bean.Girls;
import com.aqiu.rxzone.bean.Girls.TngouEntity;
import com.aqiu.rxzone.request.NetRequest;
import com.aqiu.rxzone.ui.base.BaseActivity;
import com.aqiu.rxzone.utils.L;
import com.aqiu.rxzone.utils.RxHelper;
import com.aqiu.rxzone.utils.Tutils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;


public class TestActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView_test)
    RecyclerView mRecy;
    @BindView(R.id.SwipeRefreshLayout_test)
    SwipeRefreshLayout mSwipeRefreshWidget;

    private GirlsAdapter mQuickAdapter;
    private int pager;
    private boolean isOnRefresh;
    private boolean isOnLoadMore;
    private SimpleLoadMoreView simpleLoadMoreView;

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
        //        GridLayoutManager gridLayoutManager = new GridLayoutManager(TestActivity.this, 3);
        //        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecy.setHasFixedSize(true);
        //        mRecy.setLayoutManager(gridLayoutManager);
        mRecy.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecy.setItemAnimator(new DefaultItemAnimator());
        mRecy.setPadding(8,8,8,8);
        //设置item之间的间隔
//        mRecy.addItemDecoration(new MyItemDecoration(this));
        //        mRecy.setAnimation(new AlphaAnimation(0.0f, 1.0f));
        //        mRecy.scheduleLayoutAnimation();
        //        mRecy.startLayoutAnimation();
        //        List<TngouEntity> list = new ArrayList<>();
        //        initAdapter(list);
        netRequest(pager);
    }

    @Override
    public void reDatas() {

    }


    private void initAdapter(List<TngouEntity> girls) {
        mQuickAdapter = new GirlsAdapter(girls);
        simpleLoadMoreView = new SimpleLoadMoreView();
        mQuickAdapter.setLoadMoreView(simpleLoadMoreView);
        //        mQuickAdapter.setEmptyView(emptyView);
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置显示动画
        mQuickAdapter.setOnLoadMoreListener(this);//设置上拉加载响应
        mQuickAdapter.setAutoLoadMoreSize(4);
    }

    private void netRequest(int i) {
        NetRequest.getGirlsObservable(i, 10)
                .compose(new RxHelper<Girls>() {
                    @Override
                    public void doMain() {

                    }
                }.io_main(TestActivity.this))
                .subscribe(new Subscriber<Girls>() {
                               @Override
                               public void onCompleted() {
                                   if (isOnRefresh) {
                                       mSwipeRefreshWidget.setRefreshing(false);
                                       isOnRefresh = false;
                                   }
                                   L.e("onCompleted正常");
                                   pager++;
                               }

                               @Override
                               public void onError(Throwable e) {
                                   L.e("错误");
                                   if (isOnRefresh) {
                                       mSwipeRefreshWidget.setRefreshing(false);
                                       isOnRefresh = false;
                                   }
                                   if (mQuickAdapter != null) {
                                       mQuickAdapter.loadMoreFail();
                                       switch (simpleLoadMoreView.getLoadMoreStatus()) {
                                           case LoadMoreView.STATUS_LOADING:

                                               break;
                                           case LoadMoreView.STATUS_FAIL:
                                               Tutils.showToast(TestActivity.this, "网络连接失败!");
                                               break;
                                           case LoadMoreView.STATUS_END:

                                               break;
                                       }
                                   }

                               }

                               @Override
                               public void onNext(Girls girls) {
                                   if (girls != null) {
                                       final List<TngouEntity> girlsTngou = girls.getTngou();
                                       if (isOnLoadMore) {
                                           mRecy.postDelayed(new Runnable() {
                                               @Override
                                               public void run() {
                                                   L.e("onNext执行");
                                                   mQuickAdapter.addData(girlsTngou);
                                                   L.e("数据加载执行...");
                                                   mQuickAdapter.loadMoreComplete();
                                                   isOnLoadMore = false;
                                               }
                                           }, 500);
                                       } else {
                                           initAdapter(girlsTngou);
                                           mRecy.setAdapter(mQuickAdapter);
                                       }
                                   }
                               }
                           }

                );
    }

    /**
     * 下拉刷新触发事件
     */
    @Override
    public void onRefresh() {
        //        synchronized (this) {
        pager = 1;
        isOnRefresh = true;
        netRequest(pager);
        //        }
    }

    /**
     * 上拉加载触发事件
     */
    @Override
    public void onLoadMoreRequested() {
        L.e("上拉加载触发了");
        isOnLoadMore = true;
        L.e(pager + "");
        netRequest(pager);
    }
}
