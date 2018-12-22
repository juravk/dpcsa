package com.dpcsa.jura.dpcsa.activity;

import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.dpcsa.CronApp;
import com.dpcsa.jura.compon.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public Screen getScreen() {
        return CronApp.getInstance().getCronDeclareScreens().SPLASH;
    }
//    @Override
//    public String getNameScreen() {
//        String st = CronDeclareScreens.SPLASH.nameComponent;
//        return CronDeclareScreens.SPLASH;
//    }

//    @Override
//    public void initView() {
////        ComponGlob.getInstance().baseDB.remoteToLocale(this, Api.DB_CATALOG,
////                "catalog", "catalog_id,ID;parent_id,IBLOCK_SECTION_ID;catalog_name,NAME");
//    }
}
