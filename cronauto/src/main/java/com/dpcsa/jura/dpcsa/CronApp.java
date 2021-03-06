package com.dpcsa.jura.dpcsa;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.dpcsa.jura.dpcsa.data.db.SQL;
import com.dpcsa.jura.dpcsa.params.CronAppParams;
import com.dpcsa.jura.dpcsa.params.CronDeclareScreens;
import com.dpcsa.jura.dpcsa.tools.PreferenceTool;

import com.dpcsa.jura.compon.single.DeclareParam;
import com.dpcsa.jura.compon.db.DatabaseManager;
import com.dpcsa.jura.compon.interfaces_classes.ParamDB;

public class CronApp extends MultiDexApplication {
    private static CronApp instance;
    private Context context;
//    private CronDeclareScreens cronDeclareScreens;

    public static CronApp getInstance() {
        if (instance == null) {
            instance = new CronApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        PreferenceTool.setContext(context);

        ParamDB paramDB = new ParamDB();
        paramDB.nameDB = SQL.DB_NAME;
        paramDB.versionDB = 12;
        paramDB.addTable(SQL.MARKA_TAB, SQL.MARKA_FIELDS);
        paramDB.addTable(SQL.MODEL_TAB, SQL.MODEL_FIELDS);
        paramDB.addTable(SQL.BONUS_S, SQL.BONUS_S_FIELDS);
        paramDB.addTable(SQL.MARKA_MODEL, SQL.MARKA_MODEL_FIELDS);
        paramDB.addTable(SQL.MODEL_PROD, SQL.MODEL_PROD_FIELDS);
        paramDB.addTable(SQL.MARKA_PROD, SQL.MARKA_PROD_FIELDS);
        paramDB.addTable(SQL.CATALOG_TAB, SQL.CATALOG_FIELDS);
        paramDB.addTable(SQL.BRAND_TAB, SQL.BRAND_FIELDS);
        paramDB.addTable(SQL.PRODUCT_TAB, SQL.PRODUCT_FIELDS, SQL.PRODUCT_INDEX_NAME, SQL.PRODUCT_INDEX_COLUMN);
        paramDB.addTable(SQL.ORDER_TAB, SQL.ORDER_FIELDS, SQL.ORDER_INDEX_NAME, SQL.ORDER_INDEX_COLUMN);
        paramDB.addTable(SQL.PROPERTY_TAB, SQL.PROPERTY_FIELDS, SQL.PROPERTY_INDEX_NAME, SQL.PROPERTY_INDEX_COLUMN);
        paramDB.addTable(SQL.ANALOG_TAB, SQL.ANALOG_FIELDS, SQL.ANALOG_INDEX_NAME, SQL.ANALOG_INDEX_COLUMN);
        paramDB.addTable(SQL.PRODUCT_ORDER, SQL.PRODUCT_ORDER_FIELDS, SQL.PRODUCT_ORDER_INDEX_NAME, SQL.PRODUCT_ORDER_INDEX_COLUMN);

        DeclareParam declareParam = DeclareParam.build(context)
                .setNetworkParams(new CronAppParams())
                .setDeclareScreens(new CronDeclareScreens())
                .setDB(new DatabaseManager(context, paramDB));
//        cronDeclareScreens = new CronDeclareScreens();
//        declareParam.setDeclareScreens(cronDeclareScreens);
    }

//    public CronDeclareScreens getCronDeclareScreens() {return cronDeclareScreens;}
}
