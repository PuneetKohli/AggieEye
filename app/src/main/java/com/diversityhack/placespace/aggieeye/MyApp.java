package com.diversityhack.placespace.aggieeye;

/**
 * Created by puneet on 03/02/18.
 */

public class MyApp extends android.app.Application {


  static MyApp myAppInstance;
  public MyApp() {
    myAppInstance = this;
  }
  public static MyApp getInstance() {
    return myAppInstance;
  }
}
