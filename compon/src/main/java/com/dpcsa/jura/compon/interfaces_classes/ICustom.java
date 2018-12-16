package com.dpcsa.jura.compon.interfaces_classes;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.Record;

public interface ICustom {
    public void customClick(int viewId, int position, Record record);
    public void afterBindViewHolder(int viewId, int position, Record record, RecyclerView.ViewHolder holder);
    public void beforeProcessingResponse(Field response, BaseComponent baseComponent);
    public void clickView(View viewClick, View parentView,
                          BaseComponent baseComponent, Record rec, int position);
    public void receiverWork(Intent intent);
    public void changeValue(int viewId, Field field);
}
