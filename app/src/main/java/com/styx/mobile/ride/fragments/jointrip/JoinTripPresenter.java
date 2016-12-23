package com.styx.mobile.ride.fragments.jointrip;


/**
 * Created by amal.george on 28-11-2016.
 */

class JoinTripPresenter implements JoinTripContract.Presenter {
    private JoinTripContract.View view;

    JoinTripPresenter(JoinTripContract.View view) {
        this.view = view;
    }

}
