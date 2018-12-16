package com.dpcsa.jura.compon.interfaces_classes;

import com.dpcsa.jura.compon.json_simple.ListRecords;

public interface IDbListener {
    public void onResponse(IBase iBase, ListRecords listRecords, String table, String nameAlias);
}
