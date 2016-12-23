package com.styx.mobile.ride.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.styx.mobile.ride.R;
import com.styx.mobile.ride.constants.UserAction;
import com.styx.mobile.ride.fragments.home.HomeFragment;
import com.styx.mobile.ride.fragments.hosttrip.HostTripFragment;
import com.styx.mobile.ride.fragments.jointrip.JoinTripFragment;
import com.styx.mobile.ride.fragments.signin.SignInFragment;
import com.styx.mobile.ride.interfaces.UserActionListener;
import com.styx.mobile.ride.utilities.Logger;

/**
 * Created by amal.george on 25-11-2016.
 */

public class BaseActivity extends AppCompatActivity implements UserActionListener {
    public static final String TAG = "BaseActivity";
    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    protected boolean isFragmentExistsInBackStack(String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) != null)
            return true;
        else
            return false;
    }

    public void addFragment(final int mContainer, final Fragment mFragment,
                            final String mTag) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(mContainer, mFragment, mTag);
        fragmentTransaction.addToBackStack(mTag);
        fragmentTransaction.commit();
    }

    public void popBackStack(String tag, int flag) {
        this.getSupportFragmentManager().popBackStack(tag, flag);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getTopFragment().isRoot()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public BaseFragment getTopFragment() {
        return (BaseFragment) this.getSupportFragmentManager().findFragmentById(R.id.fl_content);
    }

    @Override
    public void doUserAction(UserAction mUserAction, Bundle mBundle) {
        int mLayout = R.id.fl_content;
        Fragment mFragment;
        try {
            switch (mUserAction) {
                case HOME_SCREEN:
                    if (isFragmentExistsInBackStack(HomeFragment.TAG)) {
                        if (getTopFragment() instanceof HomeFragment)
                            return;
                        popBackStack(HomeFragment.TAG, 0);
                    } else {
                        mFragment = new HomeFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, HomeFragment.TAG);
                    }
                    break;
                case SIGN_IN_SCREEN:
                    if (isFragmentExistsInBackStack(SignInFragment.TAG)) {
                        if (getTopFragment() instanceof SignInFragment)
                            return;
                        popBackStack(SignInFragment.TAG, 0);
                    } else {
                        mFragment = new SignInFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, SignInFragment.TAG);
                    }
                    break;
                case HOST_TRIP_SCREEN:
                    if (isFragmentExistsInBackStack(HostTripFragment.TAG)) {
                        if (getTopFragment() instanceof HostTripFragment)
                            return;
                        popBackStack(HostTripFragment.TAG, 0);
                    } else {
                        mFragment = new HostTripFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, HostTripFragment.TAG);
                    }
                    break;
                case JOIN_TRIP_SCREEN:
                    if (isFragmentExistsInBackStack(JoinTripFragment.TAG)) {
                        if (getTopFragment() instanceof JoinTripFragment)
                            return;
                        popBackStack(JoinTripFragment.TAG, 0);
                    } else {
                        mFragment = new JoinTripFragment();
                        mFragment.setArguments(mBundle);
                        addFragment(mLayout, mFragment, JoinTripFragment.TAG);
                    }
                    break;
            }
        } catch (NullPointerException e) {
            Logger.e(TAG, e.toString());
        }
    }
}