package com.dpcsa.jura.compon.base;

import android.text.Html;

import com.dpcsa.jura.compon.network.CacheWork;
import com.dpcsa.jura.compon.single.ComponGlob;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.interfaces_classes.IPresenterListener;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.JsonSimple;
import com.dpcsa.jura.compon.json_simple.JsonSyntaxException;
import com.dpcsa.jura.compon.json_simple.Record;
import com.dpcsa.jura.compon.param.ParamModel;
import com.dpcsa.jura.compon.providers.VolleyInternetProvider;
import com.dpcsa.jura.compon.single.ComponPrefTool;
import com.dpcsa.jura.compon.single.Injector;

import java.util.HashMap;
import java.util.Map;

import static com.dpcsa.jura.compon.json_simple.Field.TYPE_STRING;

public class BasePresenter implements BaseInternetProvider.InternetProviderListener {
    private IBase iBase;
    private ParamModel paramModel;
    private Record data;
    Map<String, String> headers;
    private IPresenterListener listener;
    private boolean isCanceled;
    private BaseInternetProvider internetProvider;
    protected JsonSimple jsonSimple = new JsonSimple();
    protected String nameJson, json, url;
    protected int method;
    private ComponGlob componGlob;
    private ComponPrefTool preferences;
    private CacheWork cacheWork;
    
    public BasePresenter(IBase iBase, ParamModel paramModel,
                         Map<String, String> headersPar, Record data, IPresenterListener listener) {
        this.iBase = iBase;
        this.paramModel = paramModel;
        this.data = data;
        this.listener = listener;
        this.headers = headersPar;
        if (headers == null) {
            headers = new HashMap<>();
        }

        componGlob = Injector.getComponGlob();
        preferences = Injector.getPreferences();
        cacheWork = Injector.getCacheWork();
        String nameToken = componGlob.appParams.nameTokenInHeader;
        String token = preferences.getSessionToken();
        if (nameToken.length() > 0 && token.length() > 0) {
//            headers.put(nameToken, "bceee76d3c7d761c9ec92c286fb8bebcefb4225c311bb87e");
            headers.put(nameToken, token);
        }
        String nameLanguage = componGlob.appParams.nameLanguageInHeader;
        if (nameLanguage.length() > 0) {
            headers.put(nameLanguage, componGlob.language);
        }

        this.method = paramModel.method;
        long duration = paramModel.duration;
        if (method == ParamModel.GET) {
            String st = componGlob.installParam(paramModel.param, paramModel.typeParam, paramModel.url);
            url = paramModel.url + st;
        } else {
            url = paramModel.url;
        }
        if (duration > 0) {
            nameJson = url;
            json = cacheWork.getJson(nameJson);
            if (json == null) {
                startInternetProvider();
            } else {
                try {
                    listener.onResponse(jsonSimple.jsonToModel(Html.fromHtml(json).toString()));
                } catch (JsonSyntaxException e) {
                    iBase.log(e.getMessage());
                    e.printStackTrace();
                }
            }
        } else {
            startInternetProvider();
        }

    }

    public void startInternetProvider() {
        isCanceled = false;
        if (paramModel.internetProvider == null) {
            internetProvider = new VolleyInternetProvider();
            internetProvider.setParam(paramModel.method,
                    url, headers, jsonSimple.ModelToJson(data), this);
        } else {
            BaseInternetProvider bip = null;
            try {
                bip = (BaseInternetProvider) paramModel.internetProvider.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (bip != null) {
                internetProvider = bip.getThis();
                internetProvider.setParam(paramModel.method,
                        url, headers, jsonSimple.ModelToJson(data), this);
            } else {
                iBase.log("Ошибка создания internetProvider");
            }
        }
        iBase.addInternetProvider(internetProvider);
        iBase.progressStart();
    }

    public void cancel() {
        isCanceled = true;
        if (internetProvider != null) {
            internetProvider.cancel();
        }
    }

    @Override
    public void response(String response) {
        iBase.progressStop();
        if (response == null) {
            iBase.showDialog("", "no response", null);
        }
        if (paramModel.duration > 0) {
            cacheWork.addCasche(url,
                    paramModel.duration, response);
        }
        if ( ! isCanceled) {
            if (response.length() == 0) {
                listener.onResponse(new Field("", TYPE_STRING, ""));
            } else {
                Field f = null;
                try {
                    f = jsonSimple.jsonToModel(response);
                } catch (JsonSyntaxException e) {
                    iBase.log(e.getMessage());
                    iBase.showDialog(BaseInternetProvider.JSONSYNTAXERROR, e.getMessage(), null);
                    e.printStackTrace();
                }
                if (f != null && f.value != null) {
                    listener.onResponse(f);
//                    if (f.type == Field.TYPE_LIST_RECORD) {
//                        listener.onResponse(f);
//                    } else {
//                        Field f1 = ((Record) f.value).getField("data");
//                        if (f1 != null) {
//                            if (f1.type == TYPE_CLASS) {
//                                Field f2 = ((Record) f1.value).getField("items");
//                                if (f2 != null) {
//                                    listener.onResponse(f2);
//                                } else {
//                                    listener.onResponse(f1);
//                                }
//                            } else {
//                                listener.onResponse(f1);
//                            }
//                        } else {
//                            iBase.showDialog("", "no response 11111111111", null);
//                        }
//                    }
                } else {
                    iBase.log("Ошибка данных");
                }
            }
        }
    }

    @Override
    public void error(int statusCode, String message) {
        iBase.progressStop();
        iBase.showDialog(statusCode, message, null);
    }

}
