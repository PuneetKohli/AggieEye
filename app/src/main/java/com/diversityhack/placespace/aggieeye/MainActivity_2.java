package com.diversityhack.placespace.aggieeye;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.xdty.preference.colorpicker.ColorPickerDialog;
import org.xdty.preference.colorpicker.ColorPickerSwatch;

public class MainActivity_2 extends AppCompatActivity {

  @Bind(R.id.main_drawing_view)
  DrawingView mDrawingView;
  @Bind(R.id.main_color_iv)
  ImageView   mColorImageView;
  @Bind(R.id.main_stroke_iv)
  ImageView   mStrokeImageView;
  @Bind(R.id.main_undo_iv)
  ImageView   mUndoImageView;
  @Bind(R.id.main_redo_iv)
  ImageView   mRedoImageView;

  private int mCurrentBackgroundColor;
  private int mCurrentColor;
  private int mCurrentStroke;
  private static final int MAX_STROKE_WIDTH = 50;
  private Camera        mCamera;
  private CameraPreview mCameraPreview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_2);

    ButterKnife.bind(this);

    initDrawingView();
    mCamera = getCameraInstance();
    mCameraPreview = new CameraPreview(this, mCamera);
    FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
    preview.addView(mCameraPreview);
    Log.d("filename", "Call");
    getSupportActionBar().setTitle("Paint your world!");
    // mDrawingView.setBitmap();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main_2, menu);
    return true;
  }

  private Camera getCameraInstance() {
    Camera camera = null;
    try {
      camera = Camera.open();
      camera.setDisplayOrientation(90);
    } catch (Exception e) {
      // cannot get camera or does not exist
    }
    return camera;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_share:
        requestPermissionsAndSaveBitmap();
        break;
      case R.id.action_clear:
        mDrawingView.clearCanvas();
        finish();
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  private void initDrawingView() {
    mCurrentBackgroundColor = ContextCompat.getColor(this, android.R.color.white);
    mCurrentColor = ContextCompat.getColor(this, android.R.color.black);
    mCurrentStroke = 10;

    // mDrawingView.setBackgroundColor(mCurrentBackgroundColor);
    mDrawingView.setPaintColor(mCurrentColor);
    mDrawingView.setPaintStrokeWidth(mCurrentStroke);
  }

  private void startFillBackgroundDialog() {
    int[] colors = getResources().getIntArray(R.array.palette);

    ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
        colors,
        mCurrentBackgroundColor,
        5,
        ColorPickerDialog.SIZE_SMALL);

    dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {

      @Override
      public void onColorSelected(int color) {
        mCurrentBackgroundColor = color;
        mDrawingView.setBackgroundColor(mCurrentBackgroundColor);
      }

    });

    dialog.show(getFragmentManager(), "ColorPickerDialog");
  }

  private void startColorPickerDialog() {
    int[] colors = getResources().getIntArray(R.array.palette);

    ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
        colors,
        mCurrentColor,
        5,
        ColorPickerDialog.SIZE_SMALL);

    dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {

      @Override
      public void onColorSelected(int color) {
        mCurrentColor = color;
        mDrawingView.setPaintColor(mCurrentColor);
      }

    });

    dialog.show(getFragmentManager(), "ColorPickerDialog");
  }

  private void startStrokeSelectorDialog() {
    StrokeSelectorDialog dialog = StrokeSelectorDialog
        .newInstance(mCurrentStroke, MAX_STROKE_WIDTH);

    dialog.setOnStrokeSelectedListener(new StrokeSelectorDialog.OnStrokeSelectedListener() {
      @Override
      public void onStrokeSelected(int stroke) {
        mCurrentStroke = stroke;
        mDrawingView.setPaintStrokeWidth(mCurrentStroke);
      }
    });

    dialog.show(getSupportFragmentManager(), "StrokeSelectorDialog");
  }

  private void startShareDialog(Uri uri) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_SEND);
    intent.setType("image/*");

    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
    intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
    intent.putExtra(Intent.EXTRA_STREAM, uri);
    startActivity(Intent.createChooser(intent, "Share Image"));
  }

  private void requestPermissionsAndSaveBitmap() {
    if (PermissionManager.checkWriteStoragePermissions(this)) {
      Uri uri = FileManager.saveBitmap(mDrawingView.getBitmap());
      Toast.makeText(this, "Image Saved!", Toast.LENGTH_LONG).show();
      finish();
      //startShareDialog(uri);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions,
      int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case PermissionManager.REQUEST_WRITE_STORAGE: {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Uri uri = FileManager.saveBitmap(mDrawingView.getBitmap());
          Toast.makeText(this, "Image Saved!", Toast.LENGTH_LONG).show();
          //startShareDialog(uri);
        } else {
          Toast.makeText(this,
              "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission",
              Toast.LENGTH_LONG).show();
        }
      }
    }
  }

  @OnClick(R.id.main_color_iv)
  public void onColorOptionClick() {
    startColorPickerDialog();
  }

  @OnClick(R.id.main_stroke_iv)
  public void onStrokeOptionClick() {
    startStrokeSelectorDialog();
  }

  @OnClick(R.id.main_undo_iv)
  public void onUndoOptionClick() {
    mDrawingView.undo();
  }

  @OnClick(R.id.main_redo_iv)
  public void onRedoOptionClick() {
    mDrawingView.redo();
  }
}
