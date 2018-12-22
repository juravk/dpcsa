package com.dpcsa.jura.compon.components;

import com.dpcsa.jura.compon.adapters.StaticListAdapter;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.BaseProvider;
import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.custom_components.StaticList;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.ListRecords;
import com.dpcsa.jura.compon.param.ParamComponent;

public class StaticListComponent extends BaseComponent {
    StaticList staticList;
    StaticListAdapter adapter;

    public StaticListComponent(IBase iBase, ParamComponent paramMV, Screen multiComponent) {
        super(iBase, paramMV, multiComponent);
    }

    @Override
    public void initView() {
        if (paramMV.paramView == null || paramMV.paramView.viewId == 0) {
            staticList = (StaticList) componGlob.findViewByName(parentLayout, "baseStaticList");
        } else {
            staticList = (StaticList) parentLayout.findViewById(paramMV.paramView.viewId);
        }
        if (staticList == null) {
            iBase.log("Не найден StaticList в " + multiComponent.nameComponent);
            return;
        }
        listData = new ListRecords();
        provider = new BaseProvider(listData);
        adapter = new StaticListAdapter(this);
        staticList.setAdapter(adapter, false);
    }

    @Override
    public void changeData(Field field) {
        listData.clear();
        listData.addAll((ListRecords) field.value);
        provider.setData(listData);
        adapter.notifyDataSetChanged();
    }
}
