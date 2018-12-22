package com.dpcsa.jura.compon.interfaces_classes;

import java.util.ArrayList;
import java.util.List;

import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.param.ParamModel;

import static com.dpcsa.jura.compon.interfaces_classes.ViewHandler.TYPE;

public class ActionsAfterResponse {
    public List<ViewHandler> viewHandlers = new ArrayList<>();

    public ActionsAfterResponse startScreen(Screen screen) {
        viewHandlers.add(new ViewHandler(0, screen));
        return this;
    }

    public ActionsAfterResponse preferenceSetToken(String nameField) {
        viewHandlers.add(new ViewHandler(0, TYPE.PREFERENCE_SET_TOKEN, nameField));
        return this;
    }

    public ActionsAfterResponse showComponent(int viewId) {
        viewHandlers.add(new ViewHandler(0, TYPE.SHOW, viewId));
        return this;
    }

    public ActionsAfterResponse showComponent(int viewId, String nameFieldWithValue) {
        viewHandlers.add(new ViewHandler(viewId, TYPE.SHOW, nameFieldWithValue));
        return this;
    }

    public ActionsAfterResponse assignValue(int viewId) {
        viewHandlers.add(new ViewHandler(viewId, TYPE.ASSIGN_VALUE));
        return this;
    }

    public ActionsAfterResponse updateDataByModel(int viewId, ParamModel paramModel) {
        viewHandlers.add(new ViewHandler(viewId, TYPE.UPDATE_DATA, paramModel));
        return this;
    }

    public ActionsAfterResponse preferenceSetName(String nameField) {
        viewHandlers.add(new ViewHandler(0, TYPE.PREFERENCE_SET_NAME, nameField));
        return this;
    }

    public ActionsAfterResponse back() {
        viewHandlers.add(new ViewHandler(0, TYPE.BACK));
        return this;
    }

    public ActionsAfterResponse noActions() {
//        viewHandlers.add(new ViewHandler(0, TYPE.BACK));
        return this;
    }
}
