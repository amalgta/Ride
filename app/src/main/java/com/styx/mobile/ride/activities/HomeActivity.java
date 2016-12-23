package com.styx.mobile.ride.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.styx.mobile.ride.R;
import com.styx.mobile.ride.base.BaseActivity;
import com.styx.mobile.ride.constants.UserAction;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {
    Toolbar toolbar;
    ImageView iv_search;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Button buttonLoginLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv_sidebar);
        navigationView.setNavigationItemSelectedListener(this);
        buttonLoginLogout = (Button) findViewById(R.id.buttonLoginLogout);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                doUserAction(UserAction.HOME_SCREEN, new Bundle());
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                doUserAction(UserAction.HOME_SCREEN, new Bundle());
                break;
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        Log.e(TAG, "onAuthStateChanged:" + firebaseAuth.toString());
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            buttonLoginLogout.setVisibility(View.VISIBLE);
            buttonLoginLogout.setText("Logout" + mAuth.getCurrentUser().getDisplayName());
            buttonLoginLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                }
            });
            doUserAction(UserAction.HOME_SCREEN, new Bundle());
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            buttonLoginLogout.setVisibility(View.GONE);
            doUserAction(UserAction.SIGN_IN_SCREEN, new Bundle());
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
    }
}
