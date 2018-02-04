package com.diversityhack.placespace.aggieeye;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.GenAnimations;
import com.daimajia.androidanimations.library.Techniques;
import com.diversityhack.placespace.aggieeye.Adapters.Adapter_mainlist_events;
import com.diversityhack.placespace.aggieeye.Adapters.Adapter_picker_events;
import com.diversityhack.placespace.aggieeye.Models.DummyEvents;
import com.diversityhack.placespace.aggieeye.Models.EventCategory;
import com.diversityhack.placespace.aggieeye.Models.EventDetails;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    DetailsDialogManager.OnDialogInteractionInterface {

  private int _DEFAULT      = 0;
  private int _DETAILDIALOG = 3;
  private int _MANUAL       = 10;
  private int _AUTOMATIC    = 11;

  private String TAG = "MainActivity";
  public static MainActivity mainActivity;
  GoogleMap          mMap;
  RecyclerView       mBottomList;
  List<EventDetails> list_events;
  TextView           bottomBar_text;
  DiscreteScrollView picker;
  private int mainViewContent = _DEFAULT;
  private int currentSelection;
  GestureDetector gestureScanner;
  private int lastBottomBarButtonAccessed; //Used to detect which view was flung in GestureDecorator. None needed anymore. TODO REMOVE
  MaterialDialog detailDialog; //Not accessed after code modification to show details. TODO REMOVE
  private double map_offset    = 0; //Offset used to prevent location from hiding behind the custom views.
  private float  mapScaleValue = 16;
  public static View                 bottomHideButton;
  private       EditText             searchBox;
  private       SlidingUpPanelLayout mSlidingUpPanel;
  private SlidingUpPanelLayout.PanelState lastPanelSate        = SlidingUpPanelLayout.PanelState.COLLAPSED;
  private int                             slidingPanelDragType = _MANUAL;

  public  EventDetails                currentEvent;
  private FusedLocationProviderClient mFusedLocationClient;

  public Location userLocation = null;

  @SuppressLint("MissingPermission")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUpFonts();
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    mainActivity = this;
    bottomHideButton = findViewById(R.id.button_hide_center_fragment);
    bottomHideButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

//        authenticateUser();
    setUpMainMenu();
    setMap();
    setUpSearch();
    setBottomBar();
    setUpPickerSelector();
    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    mFusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
          @Override
          public void onSuccess(Location location) {
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
              userLocation = location;
            }
          }
        });
  }

  private void authenticateUser() {
    openActivity("login");
  }

  private void setUpSearch() {
    searchBox = (EditText) findViewById(R.id.searchBox);
    searchBox.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        filterData();
      }
    });
  }

  private void filterData() {
    String searchString = searchBox.getText().toString();
    if (searchString.equals("")) {
      return;
    }
  }

  private void setUpMainMenu() {
    final ImageView menuIcon = (ImageView) findViewById(R.id.menuIcon);
    final View customMenu = findViewById(R.id.custom_menu);
    menuIcon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (customMenu.getVisibility() == View.GONE) {
          openMenu(menuIcon, customMenu);
        } else {
          closeMenu(menuIcon, customMenu);
        }
      }
    });
    findViewById(R.id.menu_home).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeMenu(menuIcon, customMenu);
      }
    });
    findViewById(R.id.menu_profile).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeMenu(menuIcon, customMenu);
        openActivity("profile");
      }
    });
    findViewById(R.id.menu_settings).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeMenu(menuIcon, customMenu);
        openActivity("settings");
      }
    });
    findViewById(R.id.menu_about).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        closeMenu(menuIcon, customMenu);
        openActivity("about");
      }
    });
    findViewById(R.id.filterIcon).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openActivity("filter");
      }
    });
    findViewById(R.id.searchIcon).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mBottomList.getAdapter() != null) {
          initiateSearch();
        } else {
          Toast.makeText(MainActivity.this, "Please allow data to load", Toast.LENGTH_SHORT)
              .show();
        }
      }
    });

    findViewById(R.id.addnewicon).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(i);
      }
    });

    closeMenu(menuIcon, customMenu);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1) {
      EventDetails e = new EventDetails();
      e.setAR(true);
      e.setLongitude(userLocation.getLatitude());
      e.setLongitude(userLocation.getLongitude());
      e.setName("Hackathon Finals!");
      e.setAddress("Langford");
      e.setCategory(EventCategory.COMPETITION);
      e.setTime("2-5pm");
      list_events.add(e);
      adapter.notifyDataSetChanged();
    }
  }

  private void initiateSearch() {
    searchBox.setVisibility(View.VISIBLE);
    searchBox.setFocusableInTouchMode(true);
    searchBox.requestFocus();
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
    mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
  }

  private void closeSearch() {
    searchBox.setVisibility(View.GONE);
    fillBottomListWithEvents();
  }

  private void openActivity(String string) {
    if (string.equals("login")) {
      startActivity(new Intent(MainActivity.this, AuthenticationActivity.class));
      return;
    } else if (string.equals("profile")) {
      startActivity(new Intent(MainActivity.this, ProfileActivity.class));
      return;
    }
    Toast.makeText(MainActivity.this, "" + string, Toast.LENGTH_SHORT).show();
  }

  private void closeMenu(ImageView menuIcon, final View customMenu) {
    menuIcon.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.menu_white));
    GenAnimations.with(Techniques.SlideOutUp)
        .duration(300)
        .onEnd(new GenAnimations.AnimatorCallback() {
          @Override
          public void call(Animator animator) {
            customMenu.setVisibility(View.GONE);
          }
        })
        .playOn(customMenu);
  }

  private void openMenu(ImageView menuIcon, final View customMenu) {
    menuIcon.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.cross_white));
    GenAnimations.with(Techniques.SlideInDown)
        .duration(300)
        .onStart(new GenAnimations.AnimatorCallback() {
          @Override
          public void call(Animator animator) {
            customMenu.setVisibility(View.VISIBLE);
          }
        })
        .playOn(customMenu);
  }

  private void setUpFonts() {
    ViewGroup group = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
    Font.setAllTextView(group, this);
  }


  private void setUpPickerSelector() {
    setEventsToPickerView();
  }

  private void setEventsToPickerView() {
    picker = (DiscreteScrollView) findViewById(R.id.pickerView);
    picker.addOnItemChangedListener(
        new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
          @Override
          public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder,
              int adapterPosition) {
            LatLng latLng = new LatLng(
                Adapter_picker_events.data.get(adapterPosition).getLatitude(),
                Adapter_picker_events.data.get(adapterPosition).getLongitude());
            mMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(latLng.latitude + map_offset, latLng.longitude),
                    mapScaleValue));
          }
        });
    picker.setItemTransformer(new ScaleTransformer.Builder()
        .setMaxScale(1.1f)
        .setMinScale(1.0f)
        .setPivotX(Pivot.X.CENTER)
        .setPivotY(Pivot.Y.BOTTOM)
        .build());
    GenAnimations.with(Techniques.SlideOutDown)
        .duration(300)
        .onEnd(new GenAnimations.AnimatorCallback() {
          @Override
          public void call(Animator animator) {
            picker.setAdapter(new Adapter_picker_events(MainActivity.this,
                getDummyEvents()));
            GenAnimations.with(Techniques.SlideInUp)
                .duration(300)
                .playOn(picker);
          }
        })
        .playOn(picker);
  }

  private void setBottomBar() {
    mBottomList = (RecyclerView) findViewById(R.id.bottom_list);
    mSlidingUpPanel = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
    mSlidingUpPanel.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
      @Override
      public void onPanelSlide(View panel, float slideOffset) {
        if (slideOffset > 0.6) {
          lastPanelSate = SlidingUpPanelLayout.PanelState.EXPANDED;
        }
        if (slidingPanelDragType == _MANUAL
            && lastPanelSate != SlidingUpPanelLayout.PanelState.COLLAPSED && slideOffset < 0.5) {
          MotionEvent motionEvent = MotionEvent.obtain(50, 50, MotionEvent.ACTION_UP, 0, 0, 0);
          mSlidingUpPanel.dispatchTouchEvent(motionEvent);
          slidingPanelDragType = _AUTOMATIC;
        }
      }

      @Override
      public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState,
          SlidingUpPanelLayout.PanelState newState) {
        if (newState == SlidingUpPanelLayout.PanelState.EXPANDED
            || newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
          lastPanelSate = newState;
          slidingPanelDragType = _MANUAL;
        }
      }
    });
    fillBottomListWithEvents();
  }

  private void fillBottomListWithEvents() {
    fillBottomListWithEvents(getDummyEvents());
  }

  Adapter_mainlist_events adapter;

  private void fillBottomListWithEvents(List<EventDetails> data) {
    adapter = new Adapter_mainlist_events(this, data);
    mBottomList.setAdapter(adapter);
    mBottomList.setLayoutManager(new LinearLayoutManager(this));
    applySlideUpAnimationToMainList();
  }

  private void applySlideUpAnimationToMainList() {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        mBottomList.setVisibility(View.VISIBLE);
        GenAnimations.with(Techniques.SlideInUp)
            .duration(200)
            .playOn(mBottomList);

      }
    }, 200);
  }

  private List<EventDetails> getDummyEvents() {
    list_events = Arrays.asList(DummyEvents.getDummyEvents());
    Location myCurrentLoc = userLocation;
    if (myCurrentLoc == null) {
      myCurrentLoc = new Location("");
      myCurrentLoc.setLatitude(30.618803);
      myCurrentLoc.setLongitude(-96.337646);
    }
    final Location newLoc = myCurrentLoc;
    Collections.sort(list_events, new Comparator<EventDetails>() {
      public int compare(EventDetails e1, EventDetails e2) {
        Location l1 = new Location("");
        Location l2 = new Location("");
        l1.setLatitude(e1.getLatitude());
        l2.setLatitude(e2.getLatitude());
        l1.setLongitude(e1.getLongitude());
        l2.setLongitude(e2.getLongitude());
        return (int) (l1.distanceTo(newLoc) - l2.distanceTo(newLoc));
      }
    });
    return list_events;
  }

  public void hideDetailDialog() {
    DetailsDialogManager dialogManager = new DetailsDialogManager(this);
    dialogManager.hideDetailDialog(MainActivity.this);
  }

  private void setMap() {
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }

  private void hideMainContent() {
    mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
    List<View> views = new ArrayList<>();
    views.add(findViewById(R.id.pickerView));
    views.add(findViewById(R.id.bottom_bar));
    views.add(findViewById(R.id.gradient_view));

    for (View view : views) {
      view.setVisibility(View.GONE);
    }
  }

  private void showMainContent() {
    mainViewContent = _DEFAULT;
    mMap.getUiSettings().setScrollGesturesEnabled(true);
    final List<View> views = new ArrayList<>();
    views.add(findViewById(R.id.pickerView));
    views.add(findViewById(R.id.bottom_bar));
    views.add(findViewById(R.id.gradient_view));

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        for (View view : views) {
          view.setVisibility(View.VISIBLE);
          mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        }
      }
    }, 200);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  public void onBackPressed() {
    if (mainViewContent != _DEFAULT) {
      if (mainViewContent == _DETAILDIALOG) {
        hideDetailDialog();
        showMainContent();
      }
    } else if (mSlidingUpPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
      mSlidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
      slidingPanelDragType = _AUTOMATIC;
    } else if (searchBox.getVisibility() == View.VISIBLE) {
      closeSearch();
    } else {
      super.onBackPressed();
    }
  }

  @SuppressLint("MissingPermission")
  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    setEventMarkers();
    mMap.setOnMarkerClickListener(this);
    mMap.setMyLocationEnabled(true);
  }

  @SuppressLint("MissingPermission")
  private void setEventMarkers() {
    LatLng latLng = null;
    int index = 0;
    for (EventDetails event : list_events) {
      latLng = new LatLng(event.getLatitude(), event.getLongitude());
      Marker marker = mMap.addMarker(new
          MarkerOptions().position(latLng).title(event.getName()));

      switch (event.getCategory()) {
        case FOOD:
          marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_food));
          break;
        case SPORTS:
          marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_sports));
          break;
        case STUDY:
          marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_study));
          break;
        case COMPETITION:
          marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_competition));
          break;
        case INFORMATION:
          marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_information));
          break;
        case ENTERTAINMENT:
          marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_entertainment));

      }

      marker.setTag(index);
      index++;
    }
    //Temp
    if (userLocation != null) {
      mMap.animateCamera(CameraUpdateFactory
          .newLatLngZoom(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()),
              mapScaleValue));
    } else {
      mMap.animateCamera(CameraUpdateFactory
          .newLatLngZoom(new LatLng(latLng.latitude + map_offset, latLng.longitude),
              mapScaleValue));
    }
  }

  @Override
  public boolean onMarkerClick(Marker marker) {
    int index = (int) marker.getTag();
    picker.smoothScrollToPosition(index);
    return true;
  }

  @Override
  public void onDetailsDialogShown() {
    mainViewContent = _DETAILDIALOG;
    hideMainContent();
    mMap.getUiSettings().setScrollGesturesEnabled(false);
  }

  @Override
  public void onDetailsDialogAnimationStart() {
    findViewById(R.id.center_fragment).setVisibility(View.VISIBLE);
  }

  @Override
  public void onDetailsDialogAnimationEnd() {
    bottomHideButton.setVisibility(View.VISIBLE);
  }

  @Override
  public void onDetailsDialogClosed() {
    bottomHideButton.setVisibility(View.GONE);
  }

  @Override
  public void onDetailsDialogCloseAnimationStart() {
    //Called when the detail dialog close animation starts. Do here what you want to see happen when the dialog close animation starts
  }

  @Override
  public void onDetailsDialogCloseAnimationEnd() {
    //Called when the detail dialog close animation ends. Do here what you want to see happen when the dialog close animation end
  }
}
