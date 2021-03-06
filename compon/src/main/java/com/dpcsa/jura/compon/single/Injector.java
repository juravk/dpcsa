package com.dpcsa.jura.compon.single;

import android.content.Context;

import com.dpcsa.jura.compon.base.BaseDB;
import com.dpcsa.jura.compon.db.UpdateDB;
import com.dpcsa.jura.compon.network.CacheWork;

public class Injector {
    private static Context context;
    private static CacheWork cacheWork;
    private static ComponGlob componGlob;
    private static ComponPrefTool preferences;
    private static BaseDB baseDB;
    private static UpdateDB updateDB;

    public static void initInjector(Context cont) {
        context = cont;
        componGlob = new ComponGlob(cont);
        preferences = new ComponPrefTool(cont);
        cacheWork = new CacheWork(context);
        updateDB = new UpdateDB(preferences);
    }

    protected static void setBaseDB(BaseDB db) {
        baseDB = db;
    }

    public static CacheWork getCacheWork() {return cacheWork;}
    public static ComponGlob getComponGlob() {return componGlob;}
    public static ComponPrefTool getPreferences() {return preferences;}
    public static BaseDB getBaseDB() {return baseDB;}
    public static UpdateDB getUpdateDB() {return updateDB;}
}
