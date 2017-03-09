package com.aqiu.rxzone.adapter;

import com.aqiu.rxzone.R;
import com.aqiu.rxzone.bean.Person;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * 直播点播itemComment的Adapter
 * Created by aqiu on 16/12/1.
 */
public class ItemCommentAdapter extends BaseQuickAdapter<Person,BaseViewHolder> {

    public ItemCommentAdapter(List<Person> list) {
        super(R.layout.item_comment_recy, list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Person person) {
        baseViewHolder.setText(R.id.tv_comment_content, person.getName());
    }
}
