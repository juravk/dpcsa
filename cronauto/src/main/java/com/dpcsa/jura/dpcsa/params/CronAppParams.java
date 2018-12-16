package com.dpcsa.jura.dpcsa.params;

import com.dpcsa.jura.dpcsa.dialogs.ErrorDialog;
import com.dpcsa.jura.dpcsa.dialogs.ProgressDialog;

import com.dpcsa.jura.compon.param.AppParams;

public class CronAppParams extends AppParams {
    @Override
    public void setParams() {
        baseUrl =  "https://kronavto.ua/api_dmfv53dcrgohiru67e/";
        nameTokenInHeader = "Authorization";
        paginationPerPage = 30;
        paginationNameParamPerPage = "itemsPerPage";
        paginationNameParamNumberPage = "page";
        classProgress = ProgressDialog.class;
        classErrorDialog = ErrorDialog.class;
//        errorDialogViewId = R.id.error_dialog;
    }
}
