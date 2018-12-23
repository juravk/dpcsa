package com.dpcsa.jura.dpcsa.params;

import android.util.Log;

import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.dpcsa.R;
import com.dpcsa.jura.dpcsa.activity.AddProductActivity;
import com.dpcsa.jura.dpcsa.activity.FilterActivity;
import com.dpcsa.jura.dpcsa.data.db.SQL;
import com.dpcsa.jura.dpcsa.data.network.Api;
import com.dpcsa.jura.dpcsa.data.network.GetData;
import com.dpcsa.jura.compon.base.DeclareScreens;
import com.dpcsa.jura.compon.interfaces_classes.Multiply;
import static com.dpcsa.jura.compon.interfaces_classes.ViewHandler.TYPE_PARAM_FOR_SCREEN.RECORD;

public class CronDeclareScreens extends DeclareScreens {

    public Screen FILTER, PRODUCT_DESCRIPT,

    HOW_BUY = fragment(R.layout.fragment_how_buy)
                .addNavigator(navigator(handler(R.id.back, VH.OPEN_DRAWER))),

    NEWS_DETAIL = activity(R.layout.activity_news_detail)
                .addNavigator(navigator(handler(R.id.back, VH.BACK)))
                .addComponent(TC.PANEL, model(ARGUMENTS), view(R.id.panel)),

    MUTUAL = fragment(R.layout.fragment_mutual)
                .addNavigator(navigator(handler(R.id.back, VH.OPEN_DRAWER)))
                .addDateDiapasonComponent(R.id.diapason, R.id.from, R.id.before)
                .addComponent(TC.RECYCLER, model(GET, Api.MUTUAL), view(R.id.recycler, R.layout.item_mutual)
                        .visibilityManager(visibility(R.id.consum, "CONSUPTION")), null),

    NEWS = fragment(R.layout.fragment_news)
                .addNavigator(navigator(handler(R.id.back, VH.OPEN_DRAWER)))
                .addComponent(TC.RECYCLER, model(GET, Api.NEWS), view(R.id.recycler, R.layout.item_news),
                        navigator(handler(0, NEWS_DETAIL, RECORD))),

    CONTACTS = fragment(R.layout.fragment_contacts)
                .addNavigator(navigator(handler(R.id.back, VH.OPEN_DRAWER)))
                .addComponent(TC.PANEL, model(GET, Api.CONTACTS), view(R.id.panel)),

    ORDER_PRODUCT = activity(R.layout.activity_order_product, "%1$s", "orderName").animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK)))
            .addPlusMinus(R.id.count, R.id.plus, R.id.minus, navigator(handler(model(UPDATE_DB, SQL.PRODUCT_ORDER,
                    "count", SQL.PRODUCT_ORDER_WHERE, "product_id"))),
            new Multiply(R.id.amount, "price", "amount"))
            .addComponent(TC.RECYCLER, model(GET_DB, SQL.PRODUCT_IN_ORDER, "orderId").row("row"),
                        view(R.id.recycler, R.layout.item_order_log_product),
                    navigator(handler(R.id.del, model(DEL_DB, SQL.PRODUCT_ORDER, SQL.PRODUCT_ORDER_WHERE, "product_id")),
                            handler(R.id.del, VH.ACTUAL)))
            .addTotalComponent(R.id.total, R.id.recycler, R.id.count, null, "amount", "count"),

    EDIT_ORDER = activity(R.layout.activity_edit_order),

    ORDER_LOG_HISTORY = fragment(R.layout.fragment_orderlog_history).animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK))),

    ORDER_LOG = fragment(R.layout.fragment_order_log)
                .addNavigator(navigator(handler(R.id.history, ORDER_LOG_HISTORY), handler(R.id.back, VH.OPEN_DRAWER)))
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.ORDER_LIST),
                        view(R.id.recycler, R.layout.item_order_log_ord),
                        navigator(handler(0, ORDER_PRODUCT, RECORD))),

    EXTRA_BONUS = setFragment("Экстра-бонус", SQL.PRODUCT_E_BONUS),
    NOVELTIES = setFragment("Новинки", SQL.PRODUCT_NOVELTIES),
    GIFT = setFragment("Подарки", SQL.PRODUCT_GIFT),
    MY_PROD = setFragment("Мои товары", SQL.PRODUCT_MY),

    BONUS_S = fragment(R.layout.fragment_bonus)
                .addNavigator(navigator(handler(R.id.back, VH.OPEN_DRAWER)))
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.BONUS_S_LIST)
                                .updateDB(SQL.BONUS_S, Api.DB_BONUS_S, 2, SQL.BONUS_S_ALIAS),
                        view(R.id.recycler, "thresholdBonuses",
                                new int[] {R.layout.item_bonus_present, R.layout.item_bonus})),

    FILTER_CATEGORY = activity( R.layout.activity_filter_category).animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK)))
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.CATEGORY_1),
                        view(R.id.recycler, "expandedLevel", new int[]{R.layout.item_catalog_1,
                                R.layout.item_catalog_2, R.layout.item_catalog_3})
                                .expanded(R.id.expand, R.id.expand, model(GET_DB, SQL.CATEGORY_2, "catalog_id"))
                                .expanded(R.id.expand, R.id.expand, model(GET_DB, SQL.CATEGORY_3, "catalog_id")),
                        navigator(handler(0, VH.RESULT_RECORD))),

    FILTER_MODEL = activity(R.layout.activity_filter_model).animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK), handler(R.id.apply, FILTER)))
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.MODEL, "marka_id")
                                .updateDB(SQL.MODEL_TAB, Api.DB_MODEL, SQL.dayMillisecond, SQL.MODEL_ALIAS),
                        view(R.id.recycler, "select",
                                new int[] {R.layout.item_filter_model, R.layout.item_filter_model_sel}).selected(100), null),

    FILTER_MARKA = activity(R.layout.activity_filter_marka).animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK)))
                .addLoadDb(SQL.MARKA_MODEL, Api.DB_MARKA_MODEL, SQL.dayMillisecond, SQL.MARKA_MODEL_ALIAS)
                .addLoadDb(SQL.MODEL_PROD, Api.DB_MODEL_PROD, SQL.dayMillisecond, SQL.MODEL_PROD_ALIAS)
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.MARKA)
                                .updateDB(SQL.MARKA_TAB, Api.DB_MARKA, SQL.dayMillisecond, SQL.MARKA_ALIAS),
                        view(R.id.recycler, "select",
                                new int[] {R.layout.item_filter_marka, R.layout.item_filter_marka_sel}).selected(),
                        navigator(handler(R.id.view_model, VH.SELECT), handler(R.id.view_model, FILTER_MODEL))),

    ADD_PRODUCT = activity(AddProductActivity.class).animate(AS.RL)
                    .addPlusMinus(R.id.count, R.id.plus, R.id.minus, null, new Multiply(R.id.amount, "price"))
                    .addComponent(TC.PANEL_ENTER, model(ARGUMENTS),
                            view(R.id.panel),
                            navigator(handler(R.id.add, VH.CLICK_SEND, model(POST_DB, SQL.PRODUCT_ORDER, SQL.PRODUCT_ORDER_PARAM),
                                    actionsAfterResponse().showComponent(R.id.inf_add_product, "orderName"), false)))
                    .addComponent(TC.RECYCLER, model(GET_DB, SQL.ORDER_LIST),
                            view(R.id.recycler, "status",
                                    new int[] {R.layout.item_order_log, R.layout.item_order_log_select}).selected(),
                            navigator(handler(0, VH.SET_PARAM))),

    DESCRIPT = fragment(R.layout.fragment_descript)
                .addComponent(TC.PANEL, model(GET_DB, SQL.PRODUCT_ID, "product_id"),
                        view(R.id.panel).visibilityManager(visibility(R.id.bonus_img, "extra_bonus")),
                        navigator(handler(R.id.add, ADD_PRODUCT, RECORD)))
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.ANALOG_ID_PRODUCT,"product_id")
                                .updateDB(SQL.ANALOG_TAB, Api.ANALOG, SQL.dayMillisecond, SQL.ANALOG_ALIAS),
                        view(R.id.recycler, R.layout.item_product_list).setSplashScreen(R.id.not_analog),
                        navigator(handler(0, PRODUCT_DESCRIPT, RECORD), handler(R.id.add, ADD_PRODUCT, RECORD),
                                handler(0, VH.BACK))),
    CHARACTERISTIC = fragment(R.layout.fragment_characteristic)
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.PROPERTY_ID_PRODUCT,"product_id")
                                .updateDB(SQL.PROPERTY_TAB, Api.PROPERTY, SQL.dayMillisecond, SQL.PROPERTY_ALIAS),
                        view(R.id.recycler, "2", new int[] {R.layout.item_property, R.layout.item_property_1})),
    BARCODE = activity(R.layout.activity_barcode).animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK),
                        handler(R.id.apply, VH.RESULT_PARAM, "barcode_scanner")))
                .addBarcodeComponent(R.id.barcode_scanner, R.id.result_scan, R.id.repeat),
    PRODUCT_LIST = activity(R.layout.activity_product_list).animate(AS.RL)
            .addNavigator(navigator(handler(R.id.back, VH.BACK), handler(R.id.barcode, BARCODE, actionsAfterResponse()
                    .updateDataByModel(R.id.recycler, model(GET_DB, SQL.PRODUCT_BARCODE, "barcode_scanner"))),
                    handler(R.id.filter, FILTER)))
            .addRecognizeVoiceComponent(R.id.microphone, R.id.search)
            .addComponent(TC.RECYCLER, model(GET_DB, SQL.PRODUCT_QUERY_ARRAY, "catalog_id")
                        .updateDB(SQL.PRODUCT_TAB, Api.DB_PRODUCT, SQL.dayMillisecond, SQL.PRODUCT_ALIAS),
                        view(R.id.recycler, R.layout.item_product_list)
                                .visibilityManager(visibility(R.id.bonus_i, "extra_bonus")),
                    navigator(handler(0, PRODUCT_DESCRIPT, RECORD), handler(R.id.add, ADD_PRODUCT, RECORD)))
            .addSearchComponent(R.id.search, model(GET_DB, SQL.PRODUCT_SEARCH, "product_name"),
                        view(R.id.recycler), null, false),
    CATALOG = fragment(R.layout.fragment_catalog)
                .addNavigator(navigator(handler(R.id.back, VH.OPEN_DRAWER)))
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.CATALOG_0)
                                .updateDB(SQL.CATALOG_TAB, Api.DB_CATALOG, SQL.dayMillisecond,
                                        SQL.CATALOG_ALIAS),
                        view(R.id.recycler, "expandedLevel", new int[]{R.layout.item_catalog_type_1,
                                R.layout.item_catalog_type_2, R.layout.item_catalog_type_3})
                                .expanded(R.id.expand, R.id.expand, model(GET_DB, SQL.CATALOG, "catalog_id")),
                        navigator(handler(0, PRODUCT_LIST, RECORD)));
    public Screen DRAWER = fragment(R.layout.fragment_drawer)
            .addMenu(model(new GetData()), view(R.id.recycler,
                    new int[]{R.layout.item_menu, R.layout.item_menu_select,
                            R.layout.item_menu_divider, R.layout.item_menu_enabled})),
    MAIN = activity(R.layout.activity_main)
            .addDrawer(R.id.drawer, new int[] {R.id.content_frame, R.id.left_drawer},
                    new Screen[]{null, DRAWER}),
    AUTH_FORGOT = fragment(R.layout.fragment_forgot),

    AUTH_LOGIN = fragment(R.layout.fragment_login)
                .addNavigator(navigator(handler(R.id.forgot, AUTH_FORGOT)))
            .addComponent(TC.PANEL_ENTER, null, view(R.id.panel),
                    navigator(handler(R.id.done, VH.CLICK_SEND,
                            model(POST, Api.LOGIN, "login,password"),
                            actionsAfterResponse()
                                    .preferenceSetToken("key")
                                    .startScreen(MAIN)
                                    .back(),
                            false, R.id.login, R.id.password))),
    AUTH_REGISTER = fragment(R.layout.fragment_register)
            .addComponent(TC.PANEL_ENTER, null, view(R.id.panel),
                    navigator(handler(R.id.done, VH.CLICK_SEND,
                            model(POST, Api.REGISTER, "last_name,name,second_name,phone,email,city"),
                            actionsAfterResponse().showComponent(R.id.send_ok, ""),
                            false, R.id.last_name,R.id.name,R.id.second_name,R.id.phone,R.id.email,R.id.city))),
    LOGIN_REGISTER =  fragment(R.layout.fragment_login_register)
            .addComponent(TC.PAGER_F, view(R.id.pager, new Screen[] {AUTH_LOGIN, AUTH_REGISTER})
                    .setTab(R.id.tabs, R.array.auth_tab_name)),
    AUTH = activity(R.layout.activity_auth).fragmentsContainer(R.id.content_frame, LOGIN_REGISTER),

    SPLASH = activity(R.layout.activity_splash).addComponentSplash(null, AUTH, MAIN);

    @Override
    public void declareScreen() {
        Log.d("QWERT","ZZZZZZZZZZZZZZZZZZZZZZ");
        PRODUCT_DESCRIPT = activity(R.layout.activity_product_descript, "%1$s", "catalog_name").animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK)))
                .addComponent(TC.PANEL, model(ARGUMENTS),
                        view(R.id.name_panel))
                .addComponent(TC.PAGER_F, view(R.id.pager,
                        new Screen[] {DESCRIPT, CHARACTERISTIC})
                        .setTab(R.id.tabs, R.array.descript_tab_name));

        FILTER = activity(FilterActivity.class).animate(AS.RL)
                .addNavigator(navigator(handler(R.id.back, VH.BACK),
                        handler(R.id.category, FILTER_CATEGORY, actionsAfterResponse()),
                        handler(R.id.marka_s, FILTER_MARKA)))
                .addLoadDb(SQL.MARKA_PROD, Api.DB_MARKA_PROD, SQL.dayMillisecond, SQL.MARKA_PROD_ALIAS)
                .addComponent(TC.RECYCLER, model(GET_DB, SQL.BRAND_LIST).updateDB(SQL.BRAND_TAB,
                        Api.DB_BRAND, SQL.dayMillisecond, SQL.BRAND_ALIAS),
                        view(R.id.recycler, "select",
                                new int[] {R.layout.item_filter, R.layout.item_filter_sel}).selected(5));
    }

    private Screen setFragment(String title, String sql) {
        return fragment(R.layout.fragment_extra_bonus, title)
                .addNavigator(navigator(handler(R.id.back, VH.OPEN_DRAWER),
                        handler(R.id.barcode, BARCODE, actionsAfterResponse().updateDataByModel(R.id.recycler,
                                model(GET_DB, SQL.PRODUCT_BARCODE, "barcode_scanner"))),
                        handler(R.id.filter, FILTER)))
                .addRecognizeVoiceComponent(R.id.microphone, R.id.search)
                .addComponent(TC.RECYCLER, model(GET_DB, sql)
                                .updateDB(SQL.PRODUCT_TAB, Api.DB_PRODUCT, SQL.dayMillisecond, SQL.PRODUCT_ALIAS),
                        view(R.id.recycler, R.layout.item_product_list)
                                .visibilityManager(visibility(R.id.bonus_i, "extra_bonus")),
                        navigator(handler(0, PRODUCT_DESCRIPT, RECORD), handler(R.id.add, ADD_PRODUCT, RECORD)))
                .addSearchComponent(R.id.search, model(GET_DB, SQL.PRODUCT_SEARCH, "product_name"),
                        view(R.id.recycler), null, false);
    }
}
