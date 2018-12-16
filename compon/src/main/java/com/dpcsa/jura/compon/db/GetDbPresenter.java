package com.dpcsa.jura.compon.db;

import android.util.Log;

import java.util.Date;

import com.dpcsa.jura.compon.ComponGlob;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.BaseDB;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.interfaces_classes.IDbListener;
import com.dpcsa.jura.compon.interfaces_classes.IPresenterListener;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.ListRecords;
import com.dpcsa.jura.compon.param.ParamModel;

public class GetDbPresenter {

    protected IBase iBase;
    protected IPresenterListener listener;
    protected ParamModel paramModel;
    protected long duration, newDate;
    protected String[] param;

    public GetDbPresenter(IBase iBase, ParamModel paramModel, String[] param, IPresenterListener listener) {
        this.iBase = iBase;
        this.listener = listener;
        this.param = param;
        this.paramModel = paramModel;
        duration = paramModel.duration;
        if (duration > 0) {
            newDate = new Date().getTime();
            long oldDate = ComponGlob.getInstance().updateDB.getDate(paramModel.updateTable);
            if ((newDate - oldDate) > duration) {
                new RemoteToLocale(iBase, paramModel.updateUrl, paramModel.updateTable, paramModel.updateAlias, dbListener);
            } else {
                Field f = null;
                if (paramModel.urlArray != null) {
                    if (paramModel.urlArrayIndex > -1) {
                        f = ComponGlob.getInstance().baseDB.get(iBase, paramModel, paramModel.urlArray[paramModel.urlArrayIndex + 1], param);
                    }
                } else {
                    f = ComponGlob.getInstance().baseDB.get(iBase, paramModel, paramModel.url, param);
                }
                listener.onResponse(f);
            }
        } else {
            Field f = null;
            if (paramModel.urlArray != null) {
                if (paramModel.urlArrayIndex > -1) {
                    f = ComponGlob.getInstance().baseDB.get(iBase, paramModel, paramModel.urlArray[paramModel.urlArrayIndex + 1], param);
                }
            } else {
                f = ComponGlob.getInstance().baseDB.get(iBase, paramModel, paramModel.url, param);
            }
            listener.onResponse(f);
        }
    }

    IDbListener dbListener = new IDbListener() {
        @Override
        public void onResponse(IBase iBase, ListRecords listRecords, String table, String nameAlias) {
            BaseDB baseDB = ComponGlob.getInstance().baseDB;
            baseDB.insertListRecord(iBase, table, listRecords, nameAlias);
            ComponGlob.getInstance().updateDB.add(paramModel.updateTable, newDate);
            Field f = null;
            if (paramModel.urlArray != null) {
                if (paramModel.urlArrayIndex > -1) {
                    f = baseDB.get(iBase, paramModel, paramModel.urlArray[paramModel.urlArrayIndex + 1], param);
                }
            } else {
                f = baseDB.get(iBase, paramModel, paramModel.url, param);
            }
            listener.onResponse(f);
        }
    };
}
