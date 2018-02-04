package com.diversityhack.placespace.aggieeye;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by puneet on 03/02/18.
 */

public class AddEventActivity extends AppCompatActivity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState,
      @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    setContentView(R.layout.activity_add_event);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
