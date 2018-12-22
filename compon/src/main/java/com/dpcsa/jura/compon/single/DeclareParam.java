package com.dpcsa.jura.compon.single;

import android.content.Context;

import com.dpcsa.jura.compon.base.BaseDB;
import com.dpcsa.jura.compon.base.DeclareScreens;
import com.dpcsa.jura.compon.param.AppParams;

public class DeclareParam {

    private static DeclareParam dp;
    private Context context;
    private ComponGlob componGlob;
    private ComponPrefTool preferences;
    
    public DeclareParam(Context context) {
        this.context = context;
        Injector.initInjector(context);
        componGlob = Injector.getComponGlob();
        preferences = Injector.getPreferences();
    }
    
    public static DeclareParam build(Context context) {
        if (dp == null) {
            dp = new DeclareParam(context);
        }
        return dp;
    }

    public DeclareParam setNetworkParams(AppParams params) {
        componGlob.appParams = params;
        return this;
    }

    public DeclareParam setListScreens(DeclareScreens declareScreens) {
        declareScreens.initScreen();
        return this;
    }

    public DeclareParam addParam(String name, String value) {
        componGlob.addParamValue(name, value);
        return this;
    }

    public DeclareParam setLocale(String locale) {
        preferences.setLocale(locale);
        return this;
    }

    public DeclareParam setDB(BaseDB baseDB) {
        Injector.setBaseDB(baseDB);
        return this;
    }
}
