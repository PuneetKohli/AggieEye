package com.diversityhack.placespace.aggieeye.Fragments.MainActivityFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.diversityhack.placespace.aggieeye.HistoryChecksActivity;
import com.diversityhack.placespace.aggieeye.MainActivity;
import com.diversityhack.placespace.aggieeye.Models.EventDetails;
import com.diversityhack.placespace.aggieeye.R;

/**
 * Created by Srujan on 6/16/2017.
 */

public class EventDetailsPage1 extends Fragment {

  private double tempLatitude  = 19.0896;
  private double tempLongitude = 72.8656;
  private EventDetails currentEvent;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {

    currentEvent = ((MainActivity) getActivity()).currentEvent;
    View view = inflater.inflate(R.layout.detail_dialog, container, false);

    view.findViewById(R.id.button_connect).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MaterialDialog dialog = new MaterialDialog.Builder(MainActivity.mainActivity)
            .title("Confirm")
            .content(
                "Your information is being shared with Mount Sinai Hospital.\n\nThe staff will be prepared for you once you arrive. Please make your way to hospital")
            .positiveText("Confirm")
            .positiveColor(getResources().getColor(R.color.local_darkBlue))
            .onPositive(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                Toast.makeText(MainActivity.this, "Confirmed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.mainActivity, HistoryChecksActivity.class));
              }
            })
            .negativeText("Cancel")
            .negativeColor(getResources().getColor(R.color.local_darkBlue))
            .onNegative(new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                Toast.makeText(MainActivity.mainActivity, "Canceled", Toast.LENGTH_SHORT).show();
              }
            })
            .show();
      }
    });

    view.findViewById(R.id.icon_directions).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
            Uri.parse("http://maps.google.com/maps?daddr=" + currentEvent.getLatitude() + ","
                + currentEvent.getLongitude()));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        MainActivity.mainActivity.startActivity(intent);
      }
    });

    view.findViewById(R.id.button_love).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        clickLove(view);
      }
    });

    view.findViewById(R.id.button_comment).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        ((EventDetailsPagerContainer) getParentFragment()).pager.setCurrentItem(1);
      }
    });

    TextView name = (TextView) view.findViewById(R.id.detail_name);
    TextView address = (TextView) view.findViewById(R.id.details_address);
    TextView description = (TextView) view.findViewById(R.id.detail_description);
    TextView host = (TextView) view.findViewById(R.id.detail_host);
    TextView org = (TextView) view.findViewById(R.id.detail_organization);
    TextView time = (TextView) view.findViewById(R.id.detail_time);
    ImageView categoryImg = (ImageView) view.findViewById(R.id.category_image);

    switch (currentEvent.getCategory()) {
      case FOOD:
        categoryImg.setImageResource(R.drawable.ic_food);
        break;
      case SPORTS:
        categoryImg.setImageResource(R.drawable.ic_sports_1);
        break;
      case STUDY:
        categoryImg.setImageResource(R.drawable.ic_study);
        break;
      case COMPETITION:
        categoryImg.setImageResource(R.drawable.ic_competition);
        break;
      case INFORMATION:
        categoryImg.setImageResource(R.drawable.ic_sports);
        break;
      case ENTERTAINMENT:
        categoryImg.setImageResource(R.drawable.ic_entertainment);
        break;
    }

    updateLoveText(view);
    name.setText(currentEvent.getName());
    address.setText(currentEvent.getAddress());
    description.setText(currentEvent.getAbout());
    host.setText(currentEvent.getHost());
    org.setText(currentEvent.getOrganization());
    time.setText(currentEvent.getTime());
    return view;
  }

  public static EventDetailsPage1 newInstance(String text) {
    EventDetailsPage1 fragment = new EventDetailsPage1();
    return fragment;
  }

  boolean selected = false;

  void clickLove(View view) {
    ImageView image = (ImageView) view.findViewById(R.id.heart_icon);
    if (!currentEvent.isDoesLove()) {
      currentEvent.setLoves(currentEvent.getLoves() + 1);
      image.setImageResource(R.drawable.ic_heart_filled);
    } else {
      currentEvent.setLoves(currentEvent.getLoves() - 1);
      image.setImageResource(R.drawable.ic_heart_empty);
    }
    currentEvent.setDoesLove(!currentEvent.isDoesLove());
    updateLoveText(view);
  }

  void updateLoveText(View view) {
    TextView loveText = (TextView) view.findViewById(R.id.love_text);
    loveText.setText(currentEvent.getLoves() + " LOVES");
    if (currentEvent.isDoesLove()) {
      ((ImageView) view.findViewById(R.id.heart_icon)).setImageResource(R.drawable.ic_heart_filled);
    } else {
      ((ImageView) view.findViewById(R.id.heart_icon)).setImageResource(R.drawable.ic_heart_empty);
    }
  }

}
