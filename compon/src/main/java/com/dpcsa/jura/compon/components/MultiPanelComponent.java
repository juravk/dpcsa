package com.dpcsa.jura.compon.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dpcsa.jura.compon.base.BaseActivity;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.interfaces_classes.ViewHandler;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.Record;
import com.dpcsa.jura.compon.json_simple.WorkWithRecordsAndViews;
import com.dpcsa.jura.compon.param.ParamComponent;

import java.util.List;

public class MultiPanelComponent extends BaseComponent {

    private WorkWithRecordsAndViews workWithRecordsAndViews = new WorkWithRecordsAndViews();
//    private ViewGroup view;
    private LayoutInflater mInflater;

    public MultiPanelComponent(IBase iBase, ParamComponent paramMV, Screen multiComponent) {
        super(iBase, paramMV, multiComponent);
    }

    @Override
    public void initView() {
        mInflater = (LayoutInflater) (iBase.getBaseActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewComponent = (ViewGroup) parentLayout.findViewById(paramMV.paramView.viewId);
        if (viewComponent == null) {
            iBase.log("Не найдена панель в " + paramMV.nameParentComponent);
        }
    }

    @Override
    public void changeData(Field field) {
        setView();
    }

    private void setView() {
        Record record = (Record) paramMV.paramModel.field.value;
        ((ViewGroup)viewComponent).removeAllViews();
        if (record != null && record.size() > 0) {
            ((ViewGroup)viewComponent).addView(mInflater.inflate(paramMV.paramView.layoutTypeId[0], null));
            workWithRecordsAndViews.RecordToView((Record) paramMV.paramModel.field.value,
                    viewComponent, this, click);
//            workWithRecordsAndViews.RecordToView((Record) paramMV.paramModel.field.value,
//                    viewComponent, paramMV.navigator, click, paramMV.paramView.visibilityArray);
        } else {
            ((ViewGroup)viewComponent).addView(mInflater.inflate(paramMV.paramView.layoutFurtherTypeId[0], null));
            workWithRecordsAndViews.RecordToView((Record) paramMV.paramModel.field.value,
                    viewComponent, this, click);
//            workWithRecordsAndViews.RecordToView((Record) paramMV.paramModel.field.value,
//                    viewComponent, paramMV.navigator, click, paramMV.paramView.visibilityArray);
        }
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int vId = v.getId();
            List<ViewHandler> viewHandlers = paramMV.navigator.viewHandlers;
            for (ViewHandler vh : viewHandlers) {
                if (vId == vh.viewId) {
                    switch (vh.type) {
                        case SEND_CHANGE_BACK :

                            break;
                        case CLOSE_DRAWER :
                            ((BaseActivity) activity).closeDrawer();
                            break;
                        case NAME_FRAGMENT :
                            iBase.startScreen(vh.nameFragment, false);
                            break;
                    }
                }
            }
        }
    };
}
