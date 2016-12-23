package com.styx.mobile.ride.fragments.hosttrip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.styx.mobile.ride.R;
import com.styx.mobile.ride.base.BaseFragment;
import com.styx.mobile.ride.constants.UserAction;
import com.styx.mobile.ride.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HostTripFragment extends BaseFragment implements HostTripContract.View, View.OnClickListener {
    public static final String TAG = "HostTripFragment";
    private HostTripContract.Presenter presenter;

    @Override
    protected void initUI() {
        setScreenTitle("Donor Blood");
        setScreenLayout(R.layout.fragment_host_trip);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new HostTripPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
