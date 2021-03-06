package com.dpcsa.jura.compon.base;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dpcsa.jura.compon.single.Injector;
import com.google.android.gms.common.api.GoogleApiClient;

import com.dpcsa.jura.compon.single.ComponGlob;
import com.dpcsa.jura.compon.interfaces_classes.AnimatePanel;
import com.dpcsa.jura.compon.interfaces_classes.EventComponent;
import com.dpcsa.jura.compon.interfaces_classes.IBase;
import com.dpcsa.jura.compon.interfaces_classes.OnResumePause;
import com.dpcsa.jura.compon.interfaces_classes.ParentModel;
import com.dpcsa.jura.compon.interfaces_classes.SetData;
import com.dpcsa.jura.compon.interfaces_classes.ViewHandler;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.JsonSimple;
import com.dpcsa.jura.compon.json_simple.JsonSyntaxException;
import com.dpcsa.jura.compon.single.ComponPrefTool;
import com.dpcsa.jura.compon.tools.Constants;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment implements IBase {
//    public abstract void initView(Bundle savedInstanceState);
    protected View parentLayout;
    private Object mObject;
    private int countProgressStart;
    private DialogFragment progressDialog;
    public List<BaseInternetProvider> listInternetProvider;
    public Screen mComponent;
    public List<EventComponent> listEvent;
    public List<ParentModel> parentModelList;
    private Bundle savedInstanceState;
    private GoogleApiClient googleApiClient;
    private List<AnimatePanel> animatePanelList;
    protected BaseActivity activity;
    private String nameMvp = null;
    public String TAG;
    public Field paramScreen;
    public List<OnResumePause> resumePauseList;
    private ComponGlob componGlob;
    private ComponPrefTool preferences;

    public BaseFragment() {
        mObject = null;
        listInternetProvider = new ArrayList<>();
        listEvent = new ArrayList<>();
        parentModelList = new ArrayList<>();
        componGlob = Injector.getComponGlob();
        TAG = componGlob.appParams.NAME_LOG_APP;
    }

    public BaseFragment getThis() {
        return this;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        activity = getBaseActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            nameMvp = bundle.getString(Constants.NAME_MVP);
            String paramJson = bundle.getString(Constants.NAME_PARAM_FOR_SCREEN);
            if (paramJson != null && paramJson.length() >0) {
                JsonSimple jsonSimple = new JsonSimple();
                try {
                    paramScreen = jsonSimple.jsonToModel(paramJson);
                } catch (JsonSyntaxException e) {
                    log(e.getMessage());
                    e.printStackTrace();
                }
            }
        } else if (savedInstanceState != null) {
            nameMvp = savedInstanceState.getString(Constants.NAME_MVP);
        }
        if (mComponent == null) {
            if (nameMvp != null && nameMvp.length() > 0) {
                mComponent = activity.getComponent(nameMvp);
            }
        }
        if (mComponent == null || mComponent.typeView == Screen.TYPE_VIEW.CUSTOM_FRAGMENT) {
            parentLayout = inflater.inflate(getLayoutId(), null, false);
        } else {
            parentLayout = inflater.inflate(mComponent.fragmentLayoutId, null, false);
        }
        if (mComponent != null) {
            TextView title = (TextView) componGlob.findViewByName(parentLayout, "title");
            if (title != null && mComponent.title != null) {
                if (mComponent.args != null && mComponent.args.length > 0) {
                    title.setText(String.format(mComponent.title, activity.setFormatParam(mComponent.args)));
                } else {
                    if (mComponent.title.length() > 0) {
                        title.setText(mComponent.title);
                    }
                }
            }
            if (mComponent.navigator != null) {
                for (ViewHandler vh : mComponent.navigator.viewHandlers) {
                    View v = parentLayout.findViewById(vh.viewId);
                    if (v != null) {
                        v.setOnClickListener(navigatorClick);
                    }
                }
            }
            if (mComponent.listSetData != null) {
                int ik = mComponent.listSetData.size();
                for (int i = 0; i < ik; i++) {
                    SetData sd = (SetData) mComponent.listSetData.get(i);
                    String value;
                    if (sd.source == 0) {
                        value = preferences.getNameString(sd.nameParam);
                    } else {
                        value = componGlob.getParamValue(sd.nameParam);
                    }
                    View v = parentLayout.findViewById(sd.viewId);
                    if (v != null) {
                        if (v instanceof TextView) {
                            ((TextView)v).setText(value);
                        }
                    }
                }
            }
            if (mComponent.moreWork != null) {
                mComponent.moreWork.startScreen();
            }
            mComponent.initComponents(this);
        }
        initView(savedInstanceState);
//        if (mComponent != null && mComponent.moreWork != null) {
//            mComponent.moreWork.startScreen();
//        }
        animatePanelList = new ArrayList<>();
        return parentLayout;
    }

    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.NAME_MVP, nameMvp);
    }

    @Override
    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    @Override
    public void startDrawerFragment(String screen, int containerFragmentId) {
    }

    public int getLayoutId() {
        return 0;
    }

    @Override
    public void log(String msg) {
        Log.i(TAG, msg);
    }

    @Override
    public void setResumePause(OnResumePause resumePause) {
        if (resumePauseList == null) {
            resumePauseList = new ArrayList<>();
        }
        resumePauseList.add(resumePause);
    }

//    @Override
//    public void setMapComponent(MapComponent mapComponent) {
//        getBaseActivity().setMapComponent(mapComponent);
//    }

    View.OnClickListener navigatorClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            for (ViewHandler vh : mComponent.navigator.viewHandlers) {
                if (vh.viewId == id) {
                    switch (vh.type) {
                        case NAME_FRAGMENT:
                            activity.startScreen(vh.screen, false);
                            break;
                        case SHOW:
                            View showView = parentLayout.findViewById(vh.showViewId);
                            if (showView instanceof AnimatePanel) {
                                ((AnimatePanel) showView).show(BaseFragment.this);
                            } else {
                                showView.setVisibility(View.VISIBLE);
                            }
                            break;
                        case BACK:
                            backPressed();
                            break;
                        case OPEN_DRAWER:
                            activity.openDrawer();
                            break;
                        case RECEIVER:
                            LocalBroadcastManager.getInstance(activity)
                                    .registerReceiver(broadcastReceiver, new IntentFilter(vh.nameFieldWithValue));
                            break;
                    }
//                    break;
                }
            }
        }
    };

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mComponent.moreWork != null) {
                mComponent.moreWork.receiverWork(intent);
            }
            LocalBroadcastManager.getInstance(activity).unregisterReceiver(broadcastReceiver);
        }
    };

    public void setModel(Screen mComponent) {
        this.mComponent = mComponent;
    }

//    public String setFormatParam(String[] args) {
//        String st = "";
//        BaseActivity ba = (BaseActivity) getActivity();
//        List<Param> paramValues = ComponGlob.getInstance().paramValues;
////        List<String> namesParams = ComponGlob.getInstance().namesParams;
////        List<String> valuesParams = ComponGlob.getInstance().valuesParams;
//        String sep = "";
////        int ik = namesParams.size();
//        for (String arg : args) {
//            String value = "";
//            for (Param paramV : paramValues) {
//                if (arg.equals(paramV.name)) {
//                    st = sep + paramV.value;
//                    sep = ",";
//                    break;
//                }
//            }
//        }
//        return st;
//    }

    @Override
    public void onStop() {
//        for (Request request : listRequests) {
//            request.cancel();
//        }

        if (mComponent != null && mComponent.moreWork != null) {
            mComponent.moreWork.stopScreen();
        }
        if (listInternetProvider != null) {
            for (BaseInternetProvider provider : listInternetProvider) {
                provider.cancel();
            }
        }
        mObject = null;
        super.onStop();
    }

    @Override
    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (resumePauseList != null) {
            for (OnResumePause rp : resumePauseList) {
                rp.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        if (resumePauseList != null) {
            for (OnResumePause rp : resumePauseList) {
                rp.onPause();
            }
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (resumePauseList != null) {
            for (OnResumePause rp : resumePauseList) {
                rp.onDestroy();
            }
        }
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void setFragmentsContainerId(int id) {

    }

    @Override
    public boolean isHideAnimatePanel() {
        int pos = animatePanelList.size();
        if (pos > 0) {
            animatePanelList.get(pos - 1).hide();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void addEvent(int sender, BaseComponent receiver) {
        listEvent.add(new EventComponent(sender, receiver));
    }

    @Override
    public void addEvent(int[] senderList, BaseComponent receiver) {
        for (int sender : senderList) {
            listEvent.add(new EventComponent(sender, receiver));
        }
    }

    @Override
    public Field getProfile() {
        return componGlob.profile;
    }

    @Override
    public void startScreen(String screen, boolean startFlag, Object object, int forResult) {

    }

    @Override
    public void backPressed() {
        ((BaseActivity) getActivity()).onBackPressed();
    }

    public boolean canBackPressed() {
        return isHideAnimatePanel();
    }

    @Override
    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }

    @Override
    public BaseFragment getBaseFragment() {
        return this;
    }

//    @Override
//    public void addRequest(Request request) {
//        listRequests.add(request);
//    }

    @Override
    public void addInternetProvider(BaseInternetProvider internetProvider) {
        listInternetProvider.add(internetProvider);
    }

    @Override
    public void sendEvent(int sender) {
        for (EventComponent ev : listEvent) {
            if (ev.eventSenderId == sender) {
                ev.eventReceiverComponent.actual();
            }
        }
    }

    @Override
    public void sendActualEvent(int sender, Object paramEvent) {
        for (EventComponent ev : listEvent) {
            if (ev.eventSenderId == sender) {
                ev.eventReceiverComponent.actualEvent(sender, paramEvent);
            }
        }
    }

    public ParentModel getParentModel(String name) {
        if (parentModelList.size() > 0) {
            for (ParentModel pm : parentModelList) {
                if (pm.nameParentModel != null && pm.nameParentModel.equals(name)) {
                    return pm;
                }
            }
        }
        ParentModel pm = new ParentModel(name);
        parentModelList.add(pm);
        return pm;
    }

    public String getName() {
        return "Base";
    }

    public void setObject(Object o) {
        mObject = o;
    }

    public Object getObject() {
        return mObject;
    }

    @Override
    public View getParentLayout() {
        return parentLayout;
    }

//    @Override
//    public void startActivitySimple(String nameMVP, Object object) {
//        activity.startActivitySimple(nameMVP, object);
//    }

    @Override
    public void startScreen(String screen, boolean startFlag, Object object) {
        activity.startScreen(screen, startFlag, object);
    }

    @Override
    public void startScreen(String screen, boolean startFlag) {
        activity.startScreen(screen, startFlag, -1);
    }
//    @Override
    public void startFragment(String nameMVP, boolean startFlag,Object object) {
        activity.startFragment(nameMVP, activity.mapFragment.get(nameMVP), startFlag, object, -1);
    }

//    @Override
//    public void progressStart(int progressId ) {
////        activity.progressStart(progressId);
//        if (progressDialog == null) {
//            progressDialog = new ProgressDialog();
//        }
//        if (countProgressStart == 0) {
//            progressDialog.show(getActivity().getFragmentManager(), "MyProgressDialog");
//        }
//        countProgressStart++;
//    }

    @Override
    public void progressStart() {
        if (componGlob.appParams.classProgress != null) {
            if (progressDialog == null) {
//            progressDialog = new ProgressDialog();
                try {
                    progressDialog = (DialogFragment) componGlob.appParams.classProgress.newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (countProgressStart == 0) {
                progressDialog.show(getActivity().getFragmentManager(), "MyProgressDialog");
            }
            countProgressStart++;
        }
    }

    @Override
    public void progressStop() {
        countProgressStart--;
        if (countProgressStart == 0 && progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

//    @Override
//    public void progressStop(int progressId) {
////        getBaseActivity().progressStop(progressId);
//        countProgressStart--;
//        if (countProgressStart == 0) {
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
//    }

    @Override
    public void showDialog(String title, String message, View.OnClickListener click) {
        activity.showDialog(title, message, click);
    }

    @Override
    public void showDialog(int statusCode, String message, View.OnClickListener click) {
        activity.showDialog(statusCode, message, click);
    }

    @Override
    public boolean isViewActive() {
        return activity.isViewActive();
    }

    @Override
    public void addAnimatePanel(AnimatePanel animatePanel) {
        animatePanelList.add(animatePanel);
    }

    @Override
    public void delAnimatePanel(AnimatePanel animatePanel) {
        animatePanelList.remove(animatePanel);
    }

    @Override
    public Field getParamScreen() {
        return paramScreen;
    }

}
