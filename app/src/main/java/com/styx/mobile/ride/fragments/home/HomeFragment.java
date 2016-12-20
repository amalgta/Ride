package com.styx.mobile.ride.fragments.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.styx.mobile.ride.R;
import com.styx.mobile.ride.base.BaseFragment;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = "SignInFragment";
    private HomeContract.Presenter presenter;

    @Override
    protected void initUI() {
        setRoot(true);
        setScreenTitle("Donor Blood");
        setScreenLayout(R.layout.fragment_home);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new HomePresenter(this);
    }

}
