package com.dpcsa.jura.compon.components;

import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.param.ParamComponent;
import com.dpcsa.jura.compon.tools.ComponPrefTool;

public class SplashComponent extends BaseComponent {
    public SplashComponent(IBase iBase, ParamComponent paramMV, Screen multiComponent) {
        super(iBase, paramMV, multiComponent);
    }

    @Override
    public void initView() {
        if (paramMV.intro != null && paramMV.intro.length() > 0
                && ! ComponPrefTool.getTutorial()) {
            iBase.startScreen(paramMV.intro, false);
        } else {
            if (paramMV.auth != null && paramMV.auth.length() > 0
                    && ComponPrefTool.getSessionToken().length() == 0) {
                iBase.startScreen(paramMV.auth, false);
            } else {
                iBase.startScreen(paramMV.main, false);
            }
        }
        iBase.backPressed();
    }

    @Override
    public void changeData(Field field) {

    }
}
