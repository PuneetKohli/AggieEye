package com.diversityhack.placespace.aggieeye.Fragments.HistoryCheckFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diversityhack.placespace.aggieeye.R;

/**
 * Created by Srujan on 6/10/2017.
 */

public class HC_Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ics_frag_second, container, false);
        return v;
    }

    public static HC_Fragment2 newInstance(String text) {

        HC_Fragment2 fragment = new HC_Fragment2();
        Bundle b = new Bundle();
        b.putString("msg", text);

        fragment.setArguments(b);

        return fragment;
    }
}
