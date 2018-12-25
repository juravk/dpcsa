package com.dpcsa.jura.dpcsa.activity;

import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.dpcsa.CronApp;
import com.dpcsa.jura.compon.base.BaseActivity;
import com.dpcsa.jura.dpcsa.params.CronDeclareScreens;

public class MainActivity extends BaseActivity {

    @Override
    public String getNameScreen() {
        return CronDeclareScreens.SPLASH;
    }

//    @Override
//    public void initView() {
////        ComponGlob.getInstance().baseDB.remoteToLocale(this, Api.DB_CATALOG,
////                "catalog", "catalog_id,ID;parent_id,IBLOCK_SECTION_ID;catalog_name,NAME");
//    }
}
