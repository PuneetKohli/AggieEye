package com.diversityhack.placespace.aggieeye.Fragments.MainActivityFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.diversityhack.placespace.aggieeye.MainActivity;
import com.diversityhack.placespace.aggieeye.Models.EventDetails;
import com.diversityhack.placespace.aggieeye.R;

/**
 * Created by Srujan on 6/16/2017.
 */

public class EventDetailsPage2 extends Fragment {

  private EventDetails currentEvent;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {

    currentEvent = ((MainActivity) getActivity()).currentEvent;
    View view = inflater.inflate(R.layout.comment_dialog, container, false);
    return view;
  }

  public static EventDetailsPage2 newInstance(String text) {
    EventDetailsPage2 fragment = new EventDetailsPage2();
    return fragment;
  }
}
