package com.diversityhack.placespace.aggieeye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class AuthenticationActivity extends AppCompatActivity implements OnMapReadyCallback {

  GoogleMap   mMap;
  FrameLayout container;
  View        loginFragment;
  View        forgotPassFragment;
  View        emailSentFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_authentication);

    container = (FrameLayout) findViewById(R.id.container);
    final LayoutInflater inflater = (LayoutInflater) getBaseContext()
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    loginFragment = inflater.inflate(R.layout.fragment_login, null);
    forgotPassFragment = inflater.inflate(R.layout.fragment_forgotpassword, null);
    emailSentFragment = inflater.inflate(R.layout.fragment_emailsent, null);

    //Prevent interaction on root view being handle by map
    findViewById(R.id.rootView).setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        return true;
      }
    });

    //Set up dummy map
    setMap();

    //Set up interaction with all the fragments
    setUpLoginFragment();
    setUpForgotPasswordFragment();
    setUpEmailSentFragment();

    //Show the initial login fragment when activity starts.
    addLoginFragmentToContainer();
  }

  private void setUpLoginFragment() {
    loginFragment.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Set up authentication.
        Intent i = new Intent(AuthenticationActivity.this, MainActivity.class);
        startActivity(i);
        finish();
      }
    });
    loginFragment.findViewById(R.id.tv_forgotPassword)
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            addForgotPasswordFragmentToContainer();
          }
        });
  }

  private void setUpForgotPasswordFragment() {
    forgotPassFragment.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    forgotPassFragment.findViewById(R.id.btn_confirm)
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            container.removeAllViews();
            container.addView(emailSentFragment);
          }
        });
  }

  private void setUpEmailSentFragment() {
    emailSentFragment.findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  private void addLoginFragmentToContainer() {
    container.removeAllViews();
    container.addView(loginFragment);
  }

  private void addForgotPasswordFragmentToContainer() {
    container.removeAllViews();
    container.addView(forgotPassFragment);
  }

  private void setMap() {
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onBackPressed() {
    if (container.getChildAt(0) == emailSentFragment) {
      addLoginFragmentToContainer();
      return;
    } else if (container.getChildAt(0) == forgotPassFragment) {
      addLoginFragmentToContainer();
      return;
    } else {
      new MaterialDialog.Builder(this)
          .title("Exit?")
          .content("Are you sure you want to exit?")
          .positiveText("Quit")
          .negativeText("Cancel")
          .onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
              finishAffinity();
            }
          })
          .show();
    }
//        super.onBackPressed();
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.980467, -79.238687), 17));
  }
}
