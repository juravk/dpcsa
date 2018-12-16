package com.dpcsa.jura.compon.interfaces_classes;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dpcsa.jura.compon.base.BaseActivity;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.Record;

public class MoreWork implements ICustom {
    public IBase iBase;
    public Screen screen;
    public BaseActivity activity;

    public void setParam(IBase iBase, Screen screen) {
        this.iBase = iBase;
        this.screen = screen;
        activity = iBase.getBaseActivity();
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

    public void startScreen() {

    }

    public void stopScreen() {

    }
}
