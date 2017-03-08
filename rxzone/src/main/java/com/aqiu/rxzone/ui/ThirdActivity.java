package com.aqiu.rxzone.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.adapter.GirlsAdapter;
import com.aqiu.rxzone.bean.Girls;
import com.aqiu.rxzone.request.NetRequest;
import com.aqiu.rxzone.ui.base.BaseActivity;
import com.aqiu.rxzone.ui.base.RxActivity;
import com.aqiu.rxzone.utils.L;
import com.aqiu.rxzone.utils.MyRecyclerViewUtil;
import com.aqiu.rxzone.utils.RxHelper;

import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

public class ThirdActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    android.support.v4.widget.SwipeRefreshLayout SwipeRefreshLayout;
    private MyRecyclerViewUtil myRecyclerViewUtil;
    private Context tContext;
    private GridLayoutManager gridLayoutManager;
    private int pager;
    private boolean isOnRefresh;
    private boolean isOnLoadMore;
    private GirlsAdapter girlsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_third;
    }

    @Override
    public void initDatas() {
        pager = 1;
        isOnRefresh = false;
        isOnLoadMore = false;
        tContext = ThirdActivity.this;
        myRecyclerViewUtil = new MyRecyclerViewUtil();
        gridLayoutManager = new GridLayoutManager(tContext, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        myRecyclerViewUtil.init(tContext, SwipeRefreshLayout, recyclerView, gridLayoutManager, new MyRecyclerViewUtil.RefreshDataListener() {
            @Override
            public void onRefresh() {
                isOnRefresh = true;
                netRequest(1);
            }

            @Override
            public void loadMore() {
                isOnLoadMore = true;
                netRequest(++pager);
            }
        }, new MyRecyclerViewUtil.OnScrolledLinstener() {
            @Override
            public void onScrollStateStoping() {
                girlsAdapter.setScrolling(false);
            }

            @Override
            public void onScrollStateScrolling() {
                girlsAdapter.setScrolling(true);
            }
        });
    }

    @Override
    public void reDatas() {
        netRequest(pager);
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
                }.io_main((RxActivity) tContext))
                .subscribe(new Subscriber<Girls>() {
                    @Override
                    public void onCompleted() {
                        if (isOnRefresh) {
                            SwipeRefreshLayout.setRefreshing(false);
                            isOnRefresh = false;
                        }

                        if (isOnLoadMore) {
                            //                            girlsAdapter.loadComplete();
                            L.e("上拉完成_正确");
                            isOnLoadMore = false;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isOnRefresh) {
                            SwipeRefreshLayout.setEnabled(false);
                            isOnRefresh = false;
                        }
                        if (isOnLoadMore) {
                            L.e("上拉完成_错误");
                            isOnLoadMore = false;
                        }
                    }

                    @Override
                    public void onNext(Girls girls) {
                        if (girls != null) {
                            final List<Girls.TngouEntity> girlsTngou = girls.getTngou();
                            girlsAdapter = new GirlsAdapter(girlsTngou);
                            myRecyclerViewUtil.initAdapter(tContext, girlsAdapter);
                            recyclerView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (isOnLoadMore) {
                                        L.e("上拉加载");
                                        girlsAdapter.addData(girlsTngou);
                                        girlsAdapter.loadComplete();
                                    } else {
                                        recyclerView.setAdapter(girlsAdapter);
                                    }
                                }
                            }, 500);

                        }
                    }
                });
    }
}
