package com.dpcsa.jura.dpcsa.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import com.dpcsa.jura.compon.base.BaseActivity;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.components.PlusMinusComponent;
import com.dpcsa.jura.compon.components.RecyclerComponent;
import com.dpcsa.jura.compon.custom_components.PlusMinus;
import com.dpcsa.jura.compon.interfaces_classes.ICustom;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.Record;
import com.dpcsa.jura.dpcsa.R;

public class FilterActivity extends BaseActivity implements ICustom {

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, parentLayout);

    }


    @Override
    public void customClick(int viewId, int position, Record record) {

    }

    @Override
    public void afterBindViewHolder(int viewId, int position, Record record, RecyclerView.ViewHolder holder) {

    }

    @Override
    public void beforeProcessingResponse(Field response, BaseComponent baseComponent) {

    }

    @Override
    public void clickView(View viewClick, View parentView, BaseComponent baseComponent, Record rec, int position) {

    }

    @Override
    public void receiverWork(Intent intent) {

    }

    @Override
    public void changeValue(int viewId, Field field) {

    }
}
