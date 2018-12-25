package com.dpcsa.jura.compon.base;

import android.content.Context;
import android.util.Log;

import com.dpcsa.jura.compon.interfaces_classes.DataFieldGet;
import com.dpcsa.jura.compon.interfaces_classes.ExecMethod;
import com.dpcsa.jura.compon.interfaces_classes.Navigator;
import com.dpcsa.jura.compon.interfaces_classes.SendAndUpdate;
import com.dpcsa.jura.compon.param.ParamView;
import com.dpcsa.jura.compon.single.ComponGlob;
import com.dpcsa.jura.compon.interfaces_classes.ActionsAfterResponse;
import com.dpcsa.jura.compon.interfaces_classes.ViewHandler;
import com.dpcsa.jura.compon.interfaces_classes.Visibility;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.param.ParamComponent;
import com.dpcsa.jura.compon.param.ParamModel;
import com.dpcsa.jura.compon.single.Injector;
import com.dpcsa.jura.compon.tools.Constants;

import java.util.Map;

//import java.lang.reflect.Field;

public class DeclareScreens<T>{
    protected ParamComponent.TC TC;
    protected Constants.AnimateScreen AS;
    protected ViewHandler.TYPE VH;
    protected int GET = ParamModel.GET, POST = ParamModel.POST,
            GET_DB = ParamModel.GET_DB, POST_DB = ParamModel.POST_DB, UPDATE_DB = ParamModel.UPDATE_DB,
            INSERT_DB = ParamModel.INSERT_DB, DEL_DB = ParamModel.DEL_DB, PARENT = ParamModel.PARENT,
            FIELD = ParamModel.FIELD, ARGUMENTS = ParamModel.ARGUMENTS,
            STRINGARRAY = ParamModel.STRINGARRAY, DATAFIELD = ParamModel.DATAFIELD;

    private Map<String, Screen> MapScreen;
    protected ComponGlob componGlob;

    public DeclareScreens() {
        componGlob = Injector.getComponGlob();
        MapScreen = componGlob.MapScreen;
    }

//    public void declareScreen() {
//
//    }

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
//        declareScreen();
//        Class c = this.getClass();
//        java.lang.reflect.Field[] ff = c.getFields();
//        ComponGlob componGlob = Injector.getComponGlob();
//        MapScreen = componGlob.MapScreen;
//        Class c = obj.getClass();
//        Field field = c.getField("name");
//        String nameValue = (String) field.get(obj)
//        for (java.lang.reflect.Field field : ff) {
////            Log.d("QWERT","initScreen TYPE="+field.getType().getSimpleName()+"<< NAME="+field.getName());
//            if (field.getType().getSimpleName().equals("Screen")) {
//                Log.d("QWERT","initScreen TYPE="+field.getType().getSimpleName()+"<< NAME="+field.getName());
//                Screen sc = null;
//                try {
//                    sc = (Screen) field.get(this);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                sc.nameComponent = field.getName();
//                MapScreen.put(sc.nameComponent, sc);
//                String par = sc.getParamModel();
//                if (par != null && par.length() > 0) {
//                    String[] param = par.split(Constants.SEPARATOR_LIST);
//                    int ik = param.length;
//                    for (int i = 0; i < ik; i++) {
//                        componGlob.addParam(param[i]);
//                    }
//                }
//            }
//        }
//        for (String value : MapScreen.values()) {
//            String par = value.getParamModel();
//            if (par != null && par.length() > 0) {
//                String[] param = par.split(Constants.SEPARATOR_LIST);
//                int ik = param.length;
//                for (int i = 0; i < ik; i++) {
//                    componGlob.addParam(param[i]);
////                    ComponGlob.getInstance().addParam(param[i]);
//                }
//            }
//        }
    }

//    public void setListParam(Screen screen) {
//        String par = screen.getParamModel();
//        if (par != null && par.length() > 0) {
//            String[] param = par.split(Constants.SEPARATOR_LIST);
//            int ik = param.length;
//            for (int i = 0; i < ik; i++) {
//                componGlob.addParam(param[i]);
//            }
//        }
//    }

    public Field getProfile() {
        return Injector.getComponGlob().profile;
    }

//    public String getString(int id) {
//        return context.getString(id);
//    }

//    public void setMapScreen(Map<String, Screen> MapScreen) {
//        this.MapScreen = MapScreen;
//    }

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
//        MapScreen.put(name, mc);
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

    public ParamModel model() {
        return new ParamModel();
    }

    public ParamModel model(int method) {
        return new ParamModel(method);
    }

    public ParamModel model(String url) {
        return new ParamModel(url);
    }

    public ParamModel model(int method, String urlOrNameParent) {
        return new ParamModel(method, urlOrNameParent);
    }

    public ParamModel model(int method, String[] urlArray, String param) {
        return new ParamModel(method, urlArray, param);
    }

    public ParamModel model(Field field) {
        return new ParamModel(field);
    }

    public ParamModel model(DataFieldGet dataFieldGet) {
        return new ParamModel(dataFieldGet);
    }

    public ParamModel model(String url, String param) {
        return new ParamModel(url, param);
    }

    public ParamModel model(int method, String table, String where, String param) {
        return new ParamModel(method, table, where, param);
    }
    public ParamModel model(int method, String table, String set, String where, String param) {
        return new ParamModel(method, table, set, where, param);
    }

    public ParamModel model(String url, String param, long duration) {
        return new ParamModel(url, param, duration);
    }

    public ParamModel model(int method, String url, String param, long duration) {
        return new ParamModel(method, url, param, duration);
    }

    public ParamModel model(int method, String urlOrNameParent, String paramOrField) {
        return new ParamModel(method, urlOrNameParent, paramOrField);
    }

    public ParamView view(int viewId) {
        return new ParamView(viewId);
    }

    public ParamView view(int viewId, int layoutItemId) {
        return new ParamView(viewId, layoutItemId);
    }

    public ParamView view(int viewId, int layoutItemId, int layoutFurtherId) {
        return new ParamView(viewId, layoutItemId, layoutFurtherId);
    }

    public ParamView view(int viewId, int[] layoutTypeId) {
        return new ParamView(viewId, layoutTypeId);
    }

    public ParamView view(int viewId, String fieldType, int[] layoutTypeId) {
        return new ParamView(viewId, fieldType, layoutTypeId);
    }

    public ParamView view(int viewId, String fieldType, int style) {
        return new ParamView(viewId, fieldType, style);
    }

    public ParamView view(int viewId, String [] screens) {
        return new ParamView(viewId, screens);
    }

    public ParamView view(int viewId, String[] screens, int[] containerId) {
        return new ParamView(viewId, screens, containerId);
    }

    public ParamView view(int viewId, String fieldType, int[] layoutTypeId, int[] layoutFurtherTypeId) {
        return new ParamView(viewId, fieldType, layoutTypeId, layoutFurtherTypeId);
    }

    public Navigator navigator() {
        return new Navigator();
    }

    public Navigator navigator(ViewHandler ... handlers) {
        return new Navigator(handlers);
    }

    public ViewHandler handler(String fieldNameFragment) {
        return new ViewHandler(fieldNameFragment);
    }

    public ViewHandler handler(int viewId, String screen) {
        return new ViewHandler(viewId, screen);
    }

    public ViewHandler handler(int viewId, String screen, ActionsAfterResponse afterResponse) {
        return new ViewHandler(viewId, screen, afterResponse);
    }

    public ViewHandler handler(int viewId, String screen, ViewHandler.TYPE_PARAM_FOR_SCREEN paramForScreen) {
        return new ViewHandler(viewId, screen, paramForScreen);
    }

    public ViewHandler handler(int viewId, ParamModel paramModel) {
        return new ViewHandler(viewId, paramModel);
    }

    public ViewHandler handler(ParamModel paramModel) {
        return new ViewHandler(0, paramModel);
    }

    public ViewHandler handler(int viewId, ViewHandler.TYPE type, ParamModel paramModel) {
        return new ViewHandler(viewId, type, paramModel);
    }

    public ViewHandler handler(int viewId, ViewHandler.TYPE type, ParamModel paramModel, String screen) {
        return new ViewHandler(viewId, type, paramModel, screen);
    }

    public ViewHandler handler(int viewId, ViewHandler.TYPE type, ParamModel paramModel,
                               String screen, boolean changeEnabled, int... mustValid) {
        return new ViewHandler(viewId, type, paramModel, screen, changeEnabled, mustValid);
    }

    public ViewHandler handler(int viewId, ViewHandler.TYPE type, ParamModel paramModel,
                         ActionsAfterResponse afterResponse, boolean changeEnabled, int... mustValid) {
        return new ViewHandler(viewId, type, paramModel, afterResponse, changeEnabled, mustValid);
    }

    public ViewHandler handler(int viewId, ExecMethod execMethod) {
        return new ViewHandler(viewId, execMethod);
    }

    public ViewHandler handler(int viewId, String namePreference, boolean value) {
        return new ViewHandler(viewId, namePreference, value);
    }

    public ViewHandler handler(int viewId, String namePreference, String value) {
        return new ViewHandler(viewId, namePreference, value);
    }

    public ViewHandler handler(int viewId, ViewHandler.TYPE type) {
        return new ViewHandler(viewId, type);
    }

    public ViewHandler handler(ViewHandler.TYPE type) {
        return new ViewHandler(type);
    }

    public ViewHandler handler(int viewId, ViewHandler.TYPE type, String nameFieldWithValue) {
        return new ViewHandler(viewId, type, nameFieldWithValue);
    }

    public ViewHandler handler(int viewId, SendAndUpdate sendAndUpdate) {
        return new ViewHandler(viewId, sendAndUpdate);
    }

    public ViewHandler handler(int viewId, int showViewId) {
        return new ViewHandler(viewId, ViewHandler.TYPE.SHOW, showViewId);
    }
}
