package com.styx.mobile.ride.fragments.home;


/**
 * Created by amal.george on 28-11-2016.
 */

class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;

    HomePresenter(HomeContract.View view) {
        this.view = view;
    }

}
