package com.styx.mobile.ride.fragments.hosttrip;


/**
 * Created by amal.george on 28-11-2016.
 */

class HostTripPresenter implements HostTripContract.Presenter {
    private HostTripContract.View view;

    HostTripPresenter(HostTripContract.View view) {
        this.view = view;
    }

}
