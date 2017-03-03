package com.aqiu.rxzone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.aqiu.rxzone.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt1)
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void reDatas() {

    }

    @OnClick(R.id.bt1)
    public void onClick() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
