package com.diversityhack.placespace.aggieeye.Fragments.MainActivityFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.diversityhack.placespace.aggieeye.R;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Srujan on 6/19/2017.
 */

public class EventDetailsPagerContainer extends Fragment {

  ViewPager           pager;
  CirclePageIndicator indicator;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.container_event_details, container, false);

    pager = (ViewPager) view.findViewById(R.id.viewPager);
    pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));

    indicator = (CirclePageIndicator) view.findViewById(R.id.viewPagerIndicator);
    indicator.setViewPager(pager);

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

  }

  private class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
      switch (pos) {
        case 0:
          return EventDetailsPage1.newInstance("fragment, Instance 1");
        case 1:
          return EventDetailsPage2.newInstance("fragment, Instance 2");
        default:
          return EventDetailsPage1.newInstance("ThirdFragment, Default");
      }
    }

    @Override
    public int getCount() {
      return 2;
    }
  }
}
