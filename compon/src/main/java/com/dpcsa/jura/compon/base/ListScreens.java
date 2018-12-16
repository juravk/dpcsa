package com.dpcsa.jura.compon.base;

import android.content.Context;

import com.dpcsa.jura.compon.ComponGlob;
import com.dpcsa.jura.compon.interfaces_classes.ActionsAfterResponse;
import com.dpcsa.jura.compon.interfaces_classes.ViewHandler;
import com.dpcsa.jura.compon.interfaces_classes.Visibility;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.param.ParamComponent;
import com.dpcsa.jura.compon.param.ParamModel;
import com.dpcsa.jura.compon.tools.Constants;

import java.util.Map;

public class ListScreens <T>{
    protected ParamComponent.TC TC;
    protected Constants.AnimateScreen AS;
    protected ViewHandler.TYPE VH;
    protected int GET = ParamModel.GET, POST = ParamModel.POST,
            GET_DB = ParamModel.GET_DB, POST_DB = ParamModel.POST_DB, UPDATE_DB = ParamModel.UPDATE_DB,
            INSERT_DB = ParamModel.INSERT_DB, DEL_DB = ParamModel.DEL_DB, PARENT = ParamModel.PARENT,
            FIELD = ParamModel.FIELD, ARGUMENTS = ParamModel.ARGUMENTS,
            STRINGARRAY = ParamModel.STRINGARRAY, DATAFIELD = ParamModel.DATAFIELD;

    private Map<String, Screen> MapScreen;
    protected Context context;
    protected ComponGlob componGlob;

    public void initScreen() {
        for (Screen value : MapScreen.values()) {
            String par = value.getParamModel();
            if (par != null && par.length() > 0) {
                String[] param = par.split(Constants.SEPARATOR_LIST);
                int ik = param.length;
                for (int i = 0; i < ik; i++) {
                    componGlob.addParam(param[i]);
//                    ComponGlob.getInstance().addParam(param[i]);
                }
            }
        }
    }

    public Field getProfile() {
        return componGlob.profile;
    }

    public ListScreens(Context context) {
        componGlob = ComponGlob.getInstance();
        this.context = context;
    }

//    public String getString(int id) {
//        return context.getString(id);
//    }

    public void setMapScreen(Map<String, Screen> MapScreen) {
        this.MapScreen = MapScreen;
    }

    protected Screen fragment(String name, int layoutId, String title, String... args) {
        Screen mc = new Screen(name, layoutId, title, args);
        mc.typeView = Screen.TYPE_VIEW.FRAGMENT;
        MapScreen.put(name, mc);
        return mc;
    }

    protected Screen fragment(String name, int layoutId, Class<T> additionalWork) {
        Screen mc = new Screen(name, layoutId);
        mc.typeView = Screen.TYPE_VIEW.FRAGMENT;
        mc.additionalWork = additionalWork;
        MapScreen.put(name, mc);
        return mc;
    }

    protected Screen fragment(String name, int layoutId) {
        Screen mc = new Screen(name, layoutId);
        mc.typeView = Screen.TYPE_VIEW.FRAGMENT;
        MapScreen.put(name, mc);
        return mc;
    }

    protected Screen fragment(String name, Class customFragment) {
        Screen mc = new Screen(name, customFragment);
        mc.typeView = Screen.TYPE_VIEW.CUSTOM_FRAGMENT;
        MapScreen.put(name, mc);
        return mc;
    }

//    protected Screen customFragment(String name) {
//        Screen mc = new Screen(name);
//        MapScreen.put(name, mc);
//        return mc;
//    }

    protected Screen activity(String name, Class customActivity) {
        Screen mc = new Screen(name, customActivity);
        mc.typeView = Screen.TYPE_VIEW.CUSTOM_ACTIVITY;
        MapScreen.put(name, mc);
        return mc;
    }

    protected Screen activity(String name, int layoutId, String title, String... args) {
        Screen mc = new Screen(name, layoutId, title, args);
        mc.typeView = Screen.TYPE_VIEW.ACTIVITY;
        MapScreen.put(name, mc);
        return mc;
    }

    protected Screen activity(String name, int layoutId) {
        Screen mc = new Screen(name, layoutId);
        mc.typeView = Screen.TYPE_VIEW.ACTIVITY;
        MapScreen.put(name, mc);
        return mc;
    }

    protected Screen activity(String name, int layoutId, Class<T> additionalWork) {
        Screen mc = new Screen(name, layoutId);
        mc.typeView = Screen.TYPE_VIEW.ACTIVITY;
        mc.additionalWork = additionalWork;
        MapScreen.put(name, mc);
        return mc;
    }

    public static ActionsAfterResponse actionsAfterResponse() {
        return new ActionsAfterResponse();
    }

    public static Visibility[] showManager(Visibility ... args) {
        return args;
    }

    public static Visibility visibility(int viewId, String nameField) {
        return new Visibility(0, viewId, nameField);
    }

    public static Visibility enabled(int viewId, String nameField) {
        return new Visibility(1, viewId, nameField);
    }
}
