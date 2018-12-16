package com.dpcsa.jura.compon.db;

import android.util.Log;

import com.dpcsa.jura.compon.base.BasePresenter;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.interfaces_classes.IDbListener;
import com.dpcsa.jura.compon.interfaces_classes.IPresenterListener;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.ListRecords;
import com.dpcsa.jura.compon.param.ParamModel;

public class RemoteToLocale {

    private String table, nameAlias;
    private IDbListener dbListener;
    private IBase iBase;

    public RemoteToLocale(IBase iBase, String url, String table, String nameAlias, IDbListener dbListener) {
        this.iBase = iBase;
        this.nameAlias = nameAlias;
        this.table = table;
        this.dbListener = dbListener;
        ParamModel paramModel = new ParamModel(url);
//        iBase.progressStart();
        new BasePresenter(iBase, paramModel, null, null, listener);
    }

    IPresenterListener listener = new IPresenterListener() {
        @Override
        public void onResponse(Field response) {
            iBase.progressStop();
            if (response.value instanceof ListRecords) {
                ListRecords listRecords = (ListRecords) response.value;
                dbListener.onResponse(iBase, listRecords, table, nameAlias);
            } else {
                iBase.log("Type data for table " + table + " not ListRecords");
            }
        }
    };
}
