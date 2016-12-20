package com.styx.mobile.ride.fragments.signin;


/**
 * Created by amal.george on 28-11-2016.
 */

class SignInPresenter implements SignInContract.Presenter {
    private SignInContract.View mView;

    SignInPresenter(SignInContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void request() {

    }
}
