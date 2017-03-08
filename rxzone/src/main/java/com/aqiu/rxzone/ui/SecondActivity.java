package com.aqiu.rxzone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.bean.Douban;
import com.aqiu.rxzone.request.NetRequest;
import com.aqiu.rxzone.ui.base.BaseActivity;
import com.aqiu.rxzone.utils.RxHelper;
import com.aqiu.rxzone.utils.RxSubscriber;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.bt3)
    Button bt3;

    private int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_second;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void reDatas() {

    }

    @OnClick({R.id.bt2, R.id.bt3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt2:
                netTest();
                break;
            case R.id.bt3:
                //                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
                startActivity(new Intent(SecondActivity.this, TestActivity.class));
                break;
        }
    }
    //    @OnClick(R.id.bt2)
    //    public void onClick() {
    //        //        for (int i = 0; i < 5; i++) {
    //        netTest();
    //        //        }
    //    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void netTest() {
        NetRequest.getObservable("1", "250")
                .compose(new RxHelper<Douban>() {
                    @Override
                    public void doMain() {

                    }
                }.io_main(SecondActivity.this))
                .subscribe(new RxSubscriber<Douban>() {
                    @Override
                    public void _onNext(Douban d) {
                        String ss = d.getSubjects().get(10).getTitle();
                        tv2.setText(ss);
                    }
                });
    }


    //    @OnClick(R.id.bt3)
    //    public void onClick() {
    //    }
}
