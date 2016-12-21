package com.styx.mobile.ride.fragments.signin;

import com.google.firebase.auth.FirebaseUser;
import com.styx.mobile.ride.base.BasePresenter;

/**
 * Created by amal.george on 29-11-2016.
 */

interface SignInContract {
    interface View {
        void onError(String message);
    }

    interface Presenter extends BasePresenter {
        void writeNewUser(FirebaseUser user);
        void checkIfUserExists(FirebaseUser user,OnUserExistResult result);
    }

    interface OnUserExistResult {
        void onUserExist();
        void onUserNotExist();
    }
}
