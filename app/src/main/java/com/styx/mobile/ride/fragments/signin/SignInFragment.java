package com.styx.mobile.ride.fragments.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.styx.mobile.ride.R;
import com.styx.mobile.ride.base.BaseFragment;
import com.styx.mobile.ride.ui.widget.FontTextView;

import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by amal.george on 24-11-2016.
 */

public class SignInFragment extends BaseFragment implements SignInContract.View, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    public static final String TAG = "SignInFragment";
    private SignInContract.Presenter presenter;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;

    private EditText et_username, et_password;
    private Button bt_login, bt_register, bt_login_google, bt_login_facebook;

    @Override
    protected void initUI() {
        setRoot(true);
        setScreenTitle("SignIn");
        setScreenLayout(R.layout.fragment_signin);
    }

    @Override
    protected void setUI(Bundle savedInstanceState) {
        presenter = new SignInPresenter(this);
        bt_login_google = (Button) rootView.findViewById(R.id.bt_login_google);
        bt_login_facebook = (Button) rootView.findViewById(R.id.bt_login_facebook);
        bt_login = (Button) rootView.findViewById(R.id.bt_login);
        bt_register = (Button) rootView.findViewById(R.id.bt_register);

        bt_login_google.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        bt_login_facebook.setOnClickListener(this);
        bt_login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            ((FontTextView) rootView.findViewById(R.id.tv_helloworld)).setText(mAuth.getCurrentUser().getDisplayName());
        } else {
            ((FontTextView) rootView.findViewById(R.id.tv_helloworld)).setText("Not Logged In");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
            }
        }
        if (mCallbackManager != null)
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseAuthWithCredential(AuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getBase(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        getBase().hideProgressDialog();
                        if (task.isSuccessful()) {
                            getBase().onAuthStateChanged(mAuth);
                        } else {
                            Toast.makeText(getApplicationContext(), "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        getBase().showProgressDialog();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuthWithCredential(credential);
    }

    private void firebaseAuthWithFacebook(AccessToken token) {
        getBase().showProgressDialog();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuthWithCredential(credential);
    }

    private void signInWithGoogle() {
        getBase().showProgressDialog();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getBase() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signInWithFacebook() {
        com.facebook.login.LoginManager fbLoginManager = LoginManager.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        fbLoginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                firebaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
        fbLoginManager.logInWithReadPermissions(this, Arrays.asList("user_friends", "email", "public_profile"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signInWithGoogle();
                break;
            case R.id.button_facebook_login:
                signInWithFacebook();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(getBase(), "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
