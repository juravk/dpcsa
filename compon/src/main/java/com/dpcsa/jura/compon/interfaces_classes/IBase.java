package com.dpcsa.jura.compon.interfaces_classes;

import android.os.Bundle;
import android.view.View;

import com.dpcsa.jura.compon.base.Screen;
import com.google.android.gms.common.api.GoogleApiClient;

import com.dpcsa.jura.compon.base.BaseActivity;
import com.dpcsa.jura.compon.base.BaseComponent;
import com.dpcsa.jura.compon.base.BaseFragment;
import com.dpcsa.jura.compon.base.BaseInternetProvider;
import com.dpcsa.jura.compon.components.MapComponent;
import com.dpcsa.jura.compon.json_simple.Field;
import com.dpcsa.jura.compon.json_simple.Record;

public interface IBase {
    BaseActivity getBaseActivity();
    BaseFragment getBaseFragment();
    View getParentLayout();
    void addEvent(int sender, BaseComponent receiver);
    void addEvent(int[] senderList, BaseComponent receiver);
    void sendEvent(int sender);
    void sendActualEvent(int sender, Object paramEvent);
    ParentModel getParentModel(String name);
    Field getProfile();
    void startScreen(Screen screen, boolean startFlag, Object object, int forResult);
    void startScreen(Screen screen, boolean startFlag, Object object);
    void startScreen(Screen screen, boolean startFlag);
    void startDrawerFragment(Screen screen, int containerFragmentId);
    void backPressed();
    void progressStart();
    void progressStop();
    void showDialog(String title, String message, View.OnClickListener click);
    void showDialog(int statusCode, String message, View.OnClickListener click);
    boolean isViewActive();
    void setFragmentsContainerId(int id);
    Bundle getSavedInstanceState();
    void addInternetProvider(BaseInternetProvider internetProvider);
    void setGoogleApiClient(GoogleApiClient googleApiClient);
    void addAnimatePanel(AnimatePanel animatePanel);
    void delAnimatePanel(AnimatePanel animatePanel);
    Field getParamScreen();
    boolean isHideAnimatePanel();
    void log(String msg);
    void setResumePause(OnResumePause resumePause);
}
