package com.dpcsa.jura.compon.components;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dpcsa.jura.compon.single.ComponGlob;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.BaseProvider;
import com.dpcsa.jura.compon.base.BaseProviderAdapter;
import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.interfaces_classes.Navigator;
import com.dpcsa.jura.compon.interfaces_classes.ViewHandler;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.ListRecords;
import com.dpcsa.jura.compon.json_simple.Record;
import com.dpcsa.jura.compon.param.ParamComponent;
import com.dpcsa.jura.compon.presenter.ListPresenter;
import com.dpcsa.jura.compon.single.ComponPrefTool;

public class RecyclerComponent extends BaseComponent {
    RecyclerView recycler;
    public BaseProviderAdapter adapter;
    private String componentTag = "RECYCLER_";

    public RecyclerComponent(IBase iBase, ParamComponent paramMV, Screen multiComponent) {
        super(iBase, paramMV, multiComponent);
    }

    @Override
    public void initView() {
        if (paramMV.paramView == null || paramMV.paramView.viewId == 0) {
            recycler = (RecyclerView) componGlob.findViewByName(parentLayout, "recycler");
        } else {
            recycler = (RecyclerView) parentLayout.findViewById(paramMV.paramView.viewId);
        }
        if (recycler == null) {
            iBase.log("Не найден RecyclerView в " + multiComponent.nameComponent);
            return;
        }
        listData = new ListRecords();
        if (paramMV.paramView.selected) {
            if (navigator == null) {
                navigator = new Navigator(new ViewHandler(0, ViewHandler.TYPE.SELECT));
            }
            listPresenter = new ListPresenter(this);
        }
        provider = new BaseProvider(listData);
        LinearLayoutManager layoutManager;
        switch (paramMV.type) {
            case RECYCLER_GRID:
                layoutManager = new GridLayoutManager(activity, 2);
                break;
            case RECYCLER_HORIZONTAL:
                layoutManager = new LinearLayoutManager(activity);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                break;
            default:
                layoutManager = new LinearLayoutManager(activity);
        }
        recycler.setLayoutManager(layoutManager);
        adapter = new BaseProviderAdapter(this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void changeData(Field field) {
        if (listData == null) return;
        if (field == null || field.value == null) return;
        listData.clear();
        listData.addAll((ListRecords) field.value);
        provider.setData(listData);
        if (listPresenter != null) {
            int selectStart = preferences.getNameInt(componentTag + multiComponent.nameComponent, -1);
            int ik = listData.size();
            if (ik > 0) {
                if (selectStart == -1) {
                    for (int i = 0; i < ik; i++) {
                        Record r = listData.get(i);
                        int j = r.getInt(paramMV.paramView.fieldType);
                        if (j == 1) {
                            selectStart = i;
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < ik; i++) {
                        Record r = listData.get(i);
                        Field f = r.getField(paramMV.paramView.fieldType);
                        if (f == null) {
                            f = new Field(paramMV.paramView.fieldType, Field.TYPE_INTEGER, 0);
                            r.add(f);
                        }
                        if (i == selectStart) {
                            f.value = 1;
                        } else {
                            if (r.fieldToInt(f) == 1) {
                                f.value = 0;
                            }
                        }
                    }
                }
                if (selectStart < 0) {
                    selectStart = 0;
                } else if (selectStart >= ik && ik > 0) {
                    selectStart = ik - 1;
                }
                componGlob.setParam(listData.get(selectStart));
            }
            listPresenter.changeData(listData, selectStart);
        }
        adapter.notifyDataSetChanged();
        int splash = paramMV.paramView.splashScreenViewId;
        if (splash != 0) {
            View v_splash = parentLayout.findViewById(splash);
            if (v_splash != null) {
                if (listData.size() > 0) {
                    v_splash.setVisibility(View.GONE);
                } else {
                    v_splash.setVisibility(View.VISIBLE);
                }
            } else {
                iBase.log("Не найден SplashView в " + multiComponent.nameComponent);
            }
        }




//        if (listPresenter != null) {
//            int selectStart = -1;
//            int ik = listData.size();
//            for (int i = 0; i < ik; i++) {
//                Record r = listData.get(i);
//                int j = r.getInt(paramMV.paramView.fieldType);
//                if (j == 1) {
//                    selectStart = i;
//                    break;
//                }
//            }
//            listPresenter.changeData(listData, selectStart);
//        }
        iBase.sendEvent(paramMV.paramView.viewId);
    }

    @Override
    public void changeDataPosition(int position, boolean select) {
        if (paramMV.paramView.selected) {
            adapter.notifyItemChanged(position);
            if (paramMV.paramView.maxItemSelect == -1) {
                preferences.setNameInt(componentTag + multiComponent.nameComponent, position);
                if (select && selectViewHandler != null) {
                    Record record = listData.get(position);
                    componGlob.setParam(record);
//                    String st = record.getString(selectViewHandler.nameFragment);
                    String screen = (String) record.getField(selectViewHandler.nameFieldScreen).value;
                    if (screen != null && screen.length() > 0) {
                        iBase.startScreen(screen, true);
                    }
                }
            }
        }
    }
}
