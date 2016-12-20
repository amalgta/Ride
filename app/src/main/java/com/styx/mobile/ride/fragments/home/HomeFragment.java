package com.styx.mobile.ride.fragments.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.styx.mobile.ride.R;
import com.styx.mobile.ride.base.BaseFragment;
import com.styx.mobile.ride.ui.widget.FontTextView;

/**
 * Created by amal.george on 24-11-2016.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = "HomeFragment";
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
        FontTextView ftv = (FontTextView) rootView.findViewById(R.id.tv_helloworld);
        ftv.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        ftv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });
    }

}
