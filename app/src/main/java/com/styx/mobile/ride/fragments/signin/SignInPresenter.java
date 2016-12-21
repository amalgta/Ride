package com.styx.mobile.ride.fragments.signin;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.styx.mobile.ride.models.User;
import com.styx.mobile.ride.utilities.Logger;
import com.styx.mobile.ride.utilities.Utilities;

/**
 * Created by amal.george on 28-11-2016.
 */

class SignInPresenter implements SignInContract.Presenter {
    private static final String TAG = "SignInPresenter";
    private SignInContract.View view;

    SignInPresenter(SignInContract.View mView) {
        this.view = mView;
    }

    public void writeNewUser(FirebaseUser mUser) {
        String dbFile = "users";
        DatabaseReference mMessagesRef = Utilities.getDB(dbFile);
        final User newUser = new User(mUser.getUid(), Utilities.usernameFromEmail(mUser.getEmail()), mUser.getEmail());
        final DatabaseReference newUserReference = mMessagesRef.push();
        newUser.setObjectID(newUserReference.getKey());
        mMessagesRef.child(newUserReference.getKey()).setValue(newUser, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                } else {
                    view.onError("Error");
                }
            }
        });
    }

    @Override
    public void checkIfUserExists(FirebaseUser user, final SignInContract.OnUserExistResult result) {
        String file = "users";
        DatabaseReference databaseReference = Utilities.getDB(file);
        databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    result.onUserExist();
                else
                    result.onUserNotExist();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Logger.e(TAG, databaseError.toString());
            }
        });

    }
}
