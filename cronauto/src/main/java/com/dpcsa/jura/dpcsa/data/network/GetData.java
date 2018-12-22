package com.dpcsa.jura.dpcsa.data.network;

import com.dpcsa.jura.dpcsa.CronApp;
import com.dpcsa.jura.dpcsa.R;
import com.dpcsa.jura.dpcsa.params.CronDeclareScreens;

import com.dpcsa.jura.compon.base.BaseActivity;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.interfaces_classes.Menu;
import com.dpcsa.jura.compon.interfaces_classes.DataFieldGet;
import com.dpcsa.jura.compon.json_simple.Field;

public class GetData extends DataFieldGet {
    BaseActivity activity;
    CronDeclareScreens cls;
    @Override
    public Field getField(BaseComponent mComponent) {
        activity = mComponent.activity;
        cls = CronApp.getInstance().getCronDeclareScreens();
        if (mComponent.multiComponent.nameComponent != null) {
            switch (mComponent.multiComponent.nameComponent) {
                case "DRAWER": return setMenu();
            }
        }
        return null;
    }

    private Field setMenu() {
        Menu menu = new Menu()
                .item(R.drawable.list, activity.getString(R.string.m_catalog), cls.CATALOG, true)
                .item(R.drawable.extrabonus, activity.getString(R.string.m_e_bonus), cls.EXTRA_BONUS)
                .item(R.drawable.newitem, activity.getString(R.string.m_new_items), cls.NOVELTIES)
                .item(R.drawable.present, activity.getString(R.string.m_present), cls.GIFT)
                .item(R.drawable.shopping_cart, activity.getString(R.string.m_my_product), cls.MY_PROD)
                .divider()
                .item(R.drawable.sale, activity.getString(R.string.m_bonus_system), cls.BONUS_S)
                .item(R.drawable.reader, activity.getString(R.string.m_log_orders), cls.ORDER_LOG)
                .item(R.drawable.pay, activity.getString(R.string.m_mutual), cls.MUTUAL)
                .divider()
                .item(R.drawable.user, activity.getString(R.string.m_profile), cls.NOVELTIES)
                .item(R.drawable.description, activity.getString(R.string.m_news), cls.NEWS)
                .item(R.drawable.information, activity.getString(R.string.m_how_to_buy), cls.HOW_BUY)
                .item(R.drawable.pin, activity.getString(R.string.m_contacts), cls.CONTACTS);
        return menu;
    }

}
