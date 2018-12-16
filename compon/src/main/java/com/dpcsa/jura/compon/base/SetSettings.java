package com.dpcsa.jura.compon.base;

import android.content.Context;

import com.dpcsa.jura.compon.ComponGlob;
import com.dpcsa.jura.compon.interfaces_classes.ParamDB;
import com.dpcsa.jura.compon.param.AppParams;
import com.dpcsa.jura.compon.tools.ComponPrefTool;

public class SetSettings {
    public static void setNetworkParams(AppParams params) {
        ComponGlob.getInstance().appParams = params;
    }

    public static void setListScreens(ListScreens listScreens) {
        ComponGlob.getInstance().setContext(listScreens.context);
        listScreens.setMapScreen(ComponGlob.getInstance().MapScreen);
        listScreens.initScreen();
    }

    public static void addParam(String name, String value) {
        ComponGlob.getInstance().addParamValue(name, value);
    }

    public static void setLocale(String locale) {
        ComponPrefTool.setLocale(locale);
    }

    public static void setDB(BaseDB baseDB) {
        ComponGlob.getInstance().baseDB = baseDB;
    }

}
