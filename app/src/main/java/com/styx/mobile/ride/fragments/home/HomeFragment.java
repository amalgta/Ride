package com.styx.mobile.ride.fragments.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.styx.mobile.ride.R;
import com.styx.mobile.ride.base.BaseFragment;
import com.styx.mobile.ride.constants.UserAction;
import com.styx.mobile.ride.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, View.OnClickListener {
    public static final String TAG = "HostTripFragment";
    private HomeContract.Presenter presenter;
    Button buttonHostTrip, buttonJoinTrip;

    @Override
    protected void initUI() {
        setRoot(true);
        setScreenTitle("Donor Blood");
        setScreenLayout(R.layout.fragment_home);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new HomePresenter(this);
        FontTextView ftv = (FontTextView) rootView.findViewById(R.id.tv_helloworld);
        ftv.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        buttonHostTrip = (Button) rootView.findViewById(R.id.buttonHostTrip);
        buttonHostTrip.setOnClickListener(this);
        buttonJoinTrip = (Button) rootView.findViewById(R.id.buttonJoinTrip);
        buttonJoinTrip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonHostTrip:
                getBase().doUserAction(UserAction.HOST_TRIP_SCREEN, new Bundle());
                break;
            case R.id.buttonJoinTrip:
                getBase().doUserAction(UserAction.JOIN_TRIP_SCREEN, new Bundle());
                break;
        }
    }
}
