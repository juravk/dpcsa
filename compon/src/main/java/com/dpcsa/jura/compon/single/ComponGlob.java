package com.dpcsa.jura.compon.single;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

import com.dpcsa.jura.compon.base.Screen;
import com.dpcsa.jura.compon.interfaces_classes.Param;
import com.dpcsa.jura.compon.param.AppParams;
import com.dpcsa.jura.compon.param.ParamModel;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.FieldBroadcaster;
import com.dpcsa.jura.compon.json_simple.Record;
import com.dpcsa.jura.compon.tools.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponGlob {
    public FieldBroadcaster profile;
    public Context context;
    public Map<String, Screen> MapScreen;
    public AppParams appParams;
    public List<Param> paramValues = new ArrayList<>();
    public String token;
    public String language;

    public ComponGlob(Context context) {
        this.context = context;
        token = "";
        language = "uk";
        MapScreen = new HashMap<String, Screen>();
        profile = new FieldBroadcaster("profile", Field.TYPE_RECORD, null);
    }

    public void setParam(Record fields) {
        int ik = paramValues.size();
        boolean isParam;
        for (Field f: fields) {
            String name = f.name;
            if (f.value != null) {
                isParam = false;
                for (int i = 0; i < ik; i++) {
                    Param param = paramValues.get(i);
                    if (param.name.equals(name)) {
                        isParam = true;
                        setParamValue(param, f);
//                        switch (f.type) {
//                            case Field.TYPE_STRING:
//                                param.value = new String((String) f.value);
//                                break;
//                            case Field.TYPE_INTEGER:
//                                param.value = String.valueOf((Integer) f.value);
//                                break;
//                            case Field.TYPE_LONG:
//                                param.value = String.valueOf(f.value);
//                                break;
//                            case Field.TYPE_FLOAT:
//                                param.value = String.valueOf((Float) f.value);
//                                break;
//                            case Field.TYPE_DOUBLE:
//                                param.value = String.valueOf((Double) f.value);
//                                break;
//                            case Field.TYPE_BOOLEAN:
//                                param.value = String.valueOf((Boolean) f.value);
//                                break;
//                        }
                        break;
                    }
                }
                if ( ! isParam) {
                    Param nParam = new Param(name, "");
                    setParamValue(nParam, f);
                    paramValues.add(nParam);
                }
            }
        }
    }

    private void setParamValue(Param param, Field f) {
        switch (f.type) {
            case Field.TYPE_STRING:
                param.value = new String((String) f.value);
                break;
            case Field.TYPE_INTEGER:
                param.value = String.valueOf((Integer) f.value);
                break;
            case Field.TYPE_LONG:
                param.value = String.valueOf(f.value);
                break;
            case Field.TYPE_FLOAT:
                param.value = String.valueOf((Float) f.value);
                break;
            case Field.TYPE_DOUBLE:
                param.value = String.valueOf((Double) f.value);
                break;
            case Field.TYPE_BOOLEAN:
                param.value = String.valueOf((Boolean) f.value);
                break;
        }
    }

    public void addParam(String paramName) {
        for (Param param : paramValues) {
            if (paramName.equals(param.name)) {
                return;
            }
        }
        paramValues.add(new Param(paramName, ""));
    }

    public void addParamValue(String paramName, String paramValue) {
        for (Param param : paramValues) {
            if (paramName.equals(param.name)) {
                return;
            }
        }
        paramValues.add(new Param(paramName, paramValue));
    }

    public String installParamName(String param, String url) {
        String st = "";
        if (param != null && param.length() > 0) {
            if (url.contains("?")) {
                st = "&";
            } else {
                st = "?";
            }
            String[] paramArray = param.split(Constants.SEPARATOR_LIST);
            String sep = "";
            for (String paramOne : paramArray) {
                for (Param paramV : paramValues) {
                    if (param.equals(paramV.name)) {
                        String valuePar = paramV.value;
                        if (valuePar != null && valuePar.length() > 0) {
                            st = st + sep + paramOne + "=" + paramV.value;
                            sep = "&";
                        }
                        break;
                    }
                }
            }
        }
        if (st.length() == 1) {
            st = "";
        }
        return st;
    }

    public String installParamSlash(String param) {
        String st = "";
        if (param != null && param.length() > 0) {
            String[] paramArray = param.split(Constants.SEPARATOR_LIST);
            for (String par : paramArray) {
                for (Param paramV : paramValues) {
                    if (param.equals(paramV.name)) {
                        if (paramV.value != null && paramV.value.length() > 0) {
                            st = st + "/" + paramV.value;
                        }
                        break;
                    }
                }
            }
        }
        return st;
    }

    public String getParamValue(String nameParam) {
        for (Param paramV : paramValues) {
            if (nameParam.equals(paramV.name)) {
                return paramV.value;
            }
        }
        return "";
    }

    public String installParam(String param, ParamModel.TypeParam typeParam, String url) {
        switch (typeParam) {
            case NAME: return installParamName(param, url);
            case SLASH: return installParamSlash(param);
            default: return "";
        }
    }

    public View findViewByName(View v, String name) {
        View vS = null;
        ViewGroup vg;
        int id;
        String nameS = "";
        if (v instanceof ViewGroup) {
            vg = (ViewGroup) v;
            int countChild = vg.getChildCount();
            id = v.getId();
            if (id != -1) {
                try {
                    nameS = v.getContext().getResources().getResourceEntryName(id);
                } catch (Resources.NotFoundException e) {
                    nameS = "";
                }
                if (name.equals(nameS)) {
                    return v;
                }
            }
            for (int i = 0; i < countChild; i++) {
                vS = findViewByName(vg.getChildAt(i), name);
                if (vS != null) {
                    return vS;
                }
            }
        } else {
            id = v.getId();
            if (id != -1) {
                nameS = v.getContext().getResources().getResourceEntryName(id);
                if (name.equals(nameS)) return v;
            }
        }
        return vS;
    }

    public Calendar stringToDate(String st) {
        Calendar c;
        String dd = "";
        if (st.indexOf("T") > 0) {
            dd = st.split("T")[0];
        } else {
            dd = st;
        }
        String[] d = dd.split("-");
        c = new GregorianCalendar(Integer.valueOf(d[0]),
                Integer.valueOf(d[1]) - 1,
                Integer.valueOf(d[2]));
        return c;
    }

    public String TextForNumbet(int num, String t1, String t2_4, String t5_9) {
        int n1 = num % 100;
        if (n1 < 21 && n1 > 4) {
            return t5_9;
        }
        n1 = num % 10;
        if (n1 == 1) {
            return t1;
        }
        if (n1 > 1 && n1 < 5) {
            return t2_4;
        }
        return t5_9;
    }
}
