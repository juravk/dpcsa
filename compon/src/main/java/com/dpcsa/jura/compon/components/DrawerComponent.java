package com.dpcsa.jura.compon.components;

import android.support.v4.widget.DrawerLayout;

import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.param.ParamComponent;

public class DrawerComponent extends BaseComponent{

    DrawerLayout drawer;

    public DrawerComponent(IBase iBase, ParamComponent paramMV, Screen multiComponent) {
        super(iBase, paramMV, multiComponent);
    }

    @Override
    public void initView() {
        drawer = (DrawerLayout)parentLayout.findViewById(paramMV.paramView.viewId);
        if (drawer == null) {
            iBase.log( "Не найден DrawerLayout в " + multiComponent.nameComponent);
            return;
        }
        activity.drawer = drawer;
        iBase.setFragmentsContainerId(paramMV.paramView.layoutTypeId[0]);
        Screen screen = paramMV.paramView.screens[0];
        if (screen != null) {
            iBase.startScreen(screen, true);
        }
        iBase.startDrawerFragment(paramMV.paramView.screens[1], paramMV.paramView.layoutTypeId[1]);
    }

    @Override
    public void changeData(Field field) {

    }

}
