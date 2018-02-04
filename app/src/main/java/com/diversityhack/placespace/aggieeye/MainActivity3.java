package com.diversityhack.placespace.aggieeye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity3 extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main3);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    findViewById(R.id.btn_AR).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(MainActivity3.this, MainActivity_2.class);
        startActivity(i);
      }
    });

    findViewById(R.id.btn_confirm).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        finish();
      }
    });
  }

}
