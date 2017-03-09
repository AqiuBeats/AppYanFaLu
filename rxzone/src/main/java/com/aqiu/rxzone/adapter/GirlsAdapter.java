package com.aqiu.rxzone.adapter;

import android.widget.ImageView;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.application.MyApplication;
import com.aqiu.rxzone.bean.Girls.TngouEntity;
import com.aqiu.rxzone.utils.GetRandomImg;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * 图片  Adapter
 * Created by aqiu on 17/12/1.
 */
public class GirlsAdapter extends BaseQuickAdapter<TngouEntity,BaseViewHolder> {

    private boolean scroll;

    public GirlsAdapter(List<TngouEntity> data) {
        super(R.layout.img_item,data);
    }

    //    public GirlsAdapter(List<TngouEntity> list) {
//        super(R.layout.item_girl_recy, list);
//    }

    public void setScrolling(boolean scroll) {
//        L.i("scroll----state", "boolean:" + scroll);
        this.scroll = scroll;
    }

    public boolean getScrolling() {
        return scroll;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TngouEntity girlEntity) {
//        baseViewHolder.setText(R.id.tv_title, girlEntity.getTitle());
        //如果正在滑动,则不允许进行联网图片加载
        Glide
                .with(MyApplication.getObjectContext())
//                .load(WebApi.BASE_URL_IMAGE + girlEntity.getImg())
                .load(new GetRandomImg().getIdImg())
//                .placeholder(R.mipmap.ic_launcher)//设置占位图
//                .error(R.mipmap.lv_failure)//设置错误图片
                .fitCenter()
                .crossFade()
                .into((ImageView) baseViewHolder.getView(R.id.item_img));
    }
}
