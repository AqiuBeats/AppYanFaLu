package com.aqiu.rxzone.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.adapter.ItemCommentAdapter;
import com.aqiu.rxzone.bean.Person;
import com.aqiu.rxzone.ui.base.BaseActivity;
import com.aqiu.rxzone.utils.RecyclerViewLoadMoreUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ThirdActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    android.support.v4.widget.SwipeRefreshLayout SwipeRefreshLayout;

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
        List<Person> list=new ArrayList<>();
        Person person=new Person("aqiu","12");
        for (int i = 0; i < 50; i++) {
            list.add(person);
        }
        ItemCommentAdapter itemCommentAdapter=new ItemCommentAdapter(list);
        RecyclerViewLoadMoreUtil recyclerViewLoadMoreUtil=new RecyclerViewLoadMoreUtil();
        recyclerViewLoadMoreUtil.init(ThirdActivity.this, SwipeRefreshLayout, recyclerView, itemCommentAdapter, new RecyclerViewLoadMoreUtil.RefreshDataListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public boolean loadMore() {
                return false;
            }
        });

    }

    @Override
    public void reDatas() {

    }
}
