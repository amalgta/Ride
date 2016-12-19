package com.styx.mobile.ride.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.styx.mobile.ride.activities.HomeActivity;

/**
 * Created by amal.george on 24-11-2016.
 */

public class BaseFragment extends Fragment {
    final String TAG = "BaseFragment";
    String screenTitle;
    int screenLayout;
    protected View rootView;
    boolean isRoot;
    boolean isFirstTime;

    public BaseFragment() {
        isRoot = false;
        isFirstTime = true;
        initUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(screenLayout, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getScreenTitle() != null) {
            getActivity().setTitle(screenTitle);
        }
    }

    protected void initUI() {
    }

    protected void setUI(Bundle savedInstanceState) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected HomeActivity getBase() {
        return (HomeActivity) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            setUI(savedInstanceState);
        }
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public void setScreenLayout(int screenLayout) {
        this.screenLayout = screenLayout;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }


}
