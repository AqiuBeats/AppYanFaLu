package com.aqiu.rxzone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt1)
    Button bt1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void reDatas() {

    }

    @OnClick(R.id.bt1)
    public void onClick() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
