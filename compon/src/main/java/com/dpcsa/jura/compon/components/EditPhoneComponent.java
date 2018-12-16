package com.dpcsa.jura.compon.components;

import android.view.View;

import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.custom_components.EditPhone;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.interfaces_classes.OnChangeStatusListener;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.param.ParamComponent;

public class EditPhoneComponent extends BaseComponent {

    EditPhone editPhone;

    public EditPhoneComponent(IBase iBase, ParamComponent paramMV, Screen multiComponent) {
        super(iBase, paramMV, multiComponent);
    }

    @Override
    public void initView() {
        editPhone = (EditPhone) parentLayout.findViewById(paramMV.paramView.viewId);
        if (editPhone == null) {
            iBase.log( "Не найден EditPhone в " + paramMV.nameParentComponent);
            return;
        }
        editPhone.setOnChangeStatusListener(statusListener);
    }

    OnChangeStatusListener statusListener = new OnChangeStatusListener() {
        @Override
        public void changeStatus(View v, Object status) {
            iBase.sendActualEvent(paramMV.paramView.viewId, status);
//            switch ((Integer) status) {
//                case 3 :    // стало не валидным
//                    iBase.sendActualEvent(paramMV.paramView.viewId, new Boolean(false));
//                    break;
//                case 4 :    // стало валидным
//                    iBase.sendActualEvent(paramMV.paramView.viewId, new Boolean(true));
//                    break;
//            }
        }
    };

    @Override
    public void changeData(Field field) {

    }
}
