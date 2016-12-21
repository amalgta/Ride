package com.styx.mobile.ride.models;

/**
 * Created by amal.george on 21-12-2016.
 */

import com.google.firebase.database.IgnoreExtraProperties;
import com.styx.mobile.ride.base.BaseModel;


/**
 * Created by amal.george on 25-10-2016.
 */
@IgnoreExtraProperties
public class User extends BaseModel {
    private String userID;
    private String displayName;
    private String email;

    public User(String userID, String displayName, String email) {
        this.userID = userID;
        this.displayName = displayName;
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserID() {
        return userID;
    }
}