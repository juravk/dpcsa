package com.dpcsa.jura.compon.interfaces_classes;

import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.param.ParamModel;

public class ViewHandler {
    public int viewId;
    public enum TYPE {NAME_FRAGMENT, CLOSE_DRAWER, OPEN_DRAWER, MODEL_PARAM,
        BACK, PREFERENCE_SET_VALUE, PAGER_PLUS, PREFERENCE_SET_TOKEN, PREFERENCE_SET_NAME,
        FIELD_WITH_NAME_FRAGMENT, SELECT, SET_PARAM, EXEC, UPDATE_DATA, RESULT_PARAM,
        RESULT_RECORD, ASSIGN_VALUE,
        CLICK_VIEW, MAP_ROUTE, SHOW, BROADCAST, RECEIVER, CLICK_CUSTOM, DEL_RECYCLER,
        CLICK_SEND, SEND_UPDATE, SEND_CHANGE_BACK, ACTUAL}
    public TYPE type;
//    public String nameFragment;
    public String nameFieldScreen;
    public String screen;
    public ParamModel paramModel;
    public SendAndUpdate sendAndUpdate;
    public enum TYPE_PREFER {BOOLEAN, STRING};
    public TYPE_PREFER typePref;
    public enum TYPE_PARAM_FOR_SCREEN {NONE, RECORD, LIST_RECORD};
    public TYPE_PARAM_FOR_SCREEN paramForScreen = TYPE_PARAM_FOR_SCREEN.NONE;
    public boolean pref_value_boolean;
    public String pref_value_string;
    public String namePreference;
    public int[] mustValid;
    public int showViewId;
    public boolean changeEnabled;
    public boolean[] validArray;
    public String nameFieldWithValue;
    public ActionsAfterResponse afterResponse;
    public ExecMethod execMethod;

    public ViewHandler(String nameField) {
        type = TYPE.FIELD_WITH_NAME_FRAGMENT;
        this.viewId = 0;
        this.nameFieldScreen = nameField;
    }

    public ViewHandler(TYPE type) {
        this.type = type;
        this.viewId = 0;
    }

    public ViewHandler(int viewId, String screen) {
        type = TYPE.NAME_FRAGMENT;
        paramForScreen = TYPE_PARAM_FOR_SCREEN.NONE;
        this.viewId = viewId;
        this.screen = screen;
    }

    public ViewHandler(int viewId, String screen, ActionsAfterResponse afterResponse) {
        type = TYPE.NAME_FRAGMENT;
        paramForScreen = TYPE_PARAM_FOR_SCREEN.NONE;
        this.afterResponse = afterResponse;
        this.viewId = viewId;
        this.screen = screen;
    }

    public ViewHandler(int viewId, ExecMethod execMethod) {
        type = TYPE.EXEC;
        paramForScreen = TYPE_PARAM_FOR_SCREEN.NONE;
        this.viewId = viewId;
        this.execMethod = execMethod;
    }

    public ViewHandler(int viewId, String screen, TYPE_PARAM_FOR_SCREEN paramForScreen) {
        type = TYPE.NAME_FRAGMENT;
        this.paramForScreen = paramForScreen;
        this.viewId = viewId;
        this.screen = screen;
    }

    public ViewHandler(int viewId, String screen, TYPE_PARAM_FOR_SCREEN paramForScreen,
                       ActionsAfterResponse afterResponse) {
        type = TYPE.NAME_FRAGMENT;
        this.paramForScreen = paramForScreen;
        this.afterResponse = afterResponse;
        this.viewId = viewId;
        this.screen = screen;
    }

    public ViewHandler(int viewId, ParamModel paramModel) {
        type = TYPE.MODEL_PARAM;
        this.viewId = viewId;
        this.paramModel = paramModel;
    }

    public ViewHandler(int viewId, TYPE type, int showViewId) {
        this.type = type;
        this.viewId = viewId;
        this.showViewId = showViewId;
    }

    public ViewHandler(int viewId, TYPE type, ParamModel paramModel) {
        this.type = type;
        this.viewId = viewId;
        this.paramModel = paramModel;
    }

    public ViewHandler(int viewId, TYPE type, ParamModel paramModel, String screen) {
        this.type = type;
        this.viewId = viewId;
        this.screen = screen;
        this.paramModel = paramModel;
    }

    public ViewHandler(int viewId, TYPE type, ParamModel paramModel,
                       String screen, boolean changeEnabled, int... mustValid) {
        this.type = type;
        this.viewId = viewId;
        this.screen = screen;
        this.mustValid = mustValid;
        this.changeEnabled = changeEnabled;
        this.paramModel = paramModel;
    }

    public ViewHandler(int viewId, TYPE type, ParamModel paramModel,
                       ActionsAfterResponse afterResponse, boolean changeEnabled, int... mustValid) {
        this.type = type;
        this.viewId = viewId;
        this.afterResponse = afterResponse;
        this.mustValid = mustValid;
        this.changeEnabled = changeEnabled;
        this.paramModel = paramModel;
    }

    public ViewHandler(int viewId, String namePreference, boolean value) {
        this.type = TYPE.PREFERENCE_SET_VALUE;
        this.viewId = viewId;
        this.namePreference = namePreference;
        typePref = TYPE_PREFER.BOOLEAN;
        pref_value_boolean = value;
    }

    public ViewHandler(int viewId, String namePreference, String value) {
        this.type = TYPE.PREFERENCE_SET_VALUE;
        this.viewId = viewId;
        this.namePreference = namePreference;
        typePref = TYPE_PREFER.STRING;
        pref_value_string = value;
    }

    public ViewHandler(int viewId, TYPE type) {
        this.type = type;
        this.viewId = viewId;
        this.paramModel = null;
    }

    public ViewHandler(int viewId, TYPE type, String nameFieldWithValue) {
        this.type = type;
        this.viewId = viewId;
        this.nameFieldWithValue = nameFieldWithValue;
        this.paramModel = null;
    }

    public ViewHandler(int viewId, SendAndUpdate sendAndUpdate) {
        type = TYPE.SEND_UPDATE;
        this.viewId = viewId;
        this.sendAndUpdate = sendAndUpdate;
    }
}
