package com.dpcsa.jura.compon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.BaseProvider;
import com.dpcsa.jura.compon.custom_components.BaseStaticListAdapter;
import com.dpcsa.jura.compon.param.ParamComponent;
import com.dpcsa.jura.compon.json_simple.WorkWithRecordsAndViews;

public class StaticListAdapter extends BaseStaticListAdapter {

    private ParamComponent mvParam;
    private BaseProvider provider;
    private WorkWithRecordsAndViews modelToView;
    private Context context;
    private BaseComponent baseComponent;

    public StaticListAdapter(BaseComponent baseComponent) {
        this.baseComponent = baseComponent;
        this.provider = baseComponent.provider;
        context = baseComponent.iBase.getBaseActivity();
        mvParam = baseComponent.paramMV;
        modelToView = new WorkWithRecordsAndViews();
    }
    @Override
    public int getCount() {
        return provider.getCount();
    }

    @Override
    public View getView(int position) {
        View view = LayoutInflater.from(context).inflate(mvParam.paramView.layoutTypeId[0], null);
        modelToView.RecordToView(provider.get(position), view, baseComponent, null);
//        modelToView.RecordToView(provider.get(position), view, null, null, mvParam.paramView.visibilityArray);
        return view;
    }

    @Override
    public void onClickView(View view, View viewElrment, int position) {

    }
}
