package com.aqiu.rxzone.adapter;

import android.widget.ImageView;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.application.MyApplication;
import com.aqiu.rxzone.bean.Girls.TngouEntity;
import com.aqiu.rxzone.http.WebApi;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * 图片  Adapter
 * Created by aqiu on 16/12/1.
 */
public class GirlsAdapter extends BaseQuickAdapter<TngouEntity> {

    private boolean scroll;

    public GirlsAdapter(List<TngouEntity> list) {
        super(R.layout.item_girl_recy, list);
    }

    public void setScrolling(boolean scroll) {
//        L.i("scroll----state", "boolean:" + scroll);
        this.scroll = scroll;
    }

    public boolean getScrolling() {
        return scroll;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TngouEntity girlEntity) {
        baseViewHolder.setText(R.id.tv_title, girlEntity.getTitle());
        //如果正在滑动,则不允许进行联网图片加载
        //        if (!scroll) {
        Glide
                .with(MyApplication.getObjectContext())
                .load(WebApi.BASE_URL_IMAGE + girlEntity.getImg())
                .centerCrop()
                .crossFade()
                .into((ImageView) baseViewHolder.getView(R.id.lv_girl));
        //        }
    }
}
