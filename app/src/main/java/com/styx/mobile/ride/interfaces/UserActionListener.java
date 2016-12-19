package com.styx.mobile.ride.interfaces;

import android.os.Bundle;

import com.styx.mobile.ride.constants.UserAction;

/**
 * Created by amal.george on 24-11-2016.
 */

public interface UserActionListener {
    void doUserAction(UserAction mUserAction, Bundle mBundle);
}
