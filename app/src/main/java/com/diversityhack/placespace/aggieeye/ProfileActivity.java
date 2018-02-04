package com.diversityhack.placespace.aggieeye;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;

public class ProfileActivity extends AppCompatActivity {

  TagView allergiesTags;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("My Profile");

    allergiesTags = (TagView) findViewById(R.id.tags_allergies);

    allergiesTags.setOnTagDeleteListener(new TagView.OnTagDeleteListener() {
      @Override
      public void onTagDeleted(TagView tagView, Tag tag, int i) {
        tagView.remove(i);
      }
    });
    setDummyTags();
  }

  private void setDummyTags() {
    String[] tags = {"Free Food", "Hackathon", "TAMU", "Sports", "Intramural", "Movies"};
    for (String string : tags) {
      Tag tag = new Tag(string);
      tag.layoutColor = getResources().getColor(R.color.local_darkBlue);
      tag.radius = 12f;
      tag.isDeletable = true;
      allergiesTags.addTag(tag);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // API 5+ solution
        onBackPressed();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
