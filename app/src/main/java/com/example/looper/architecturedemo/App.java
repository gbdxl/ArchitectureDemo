package com.example.looper.architecturedemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Looper on 2018/3/2.
 */

public class App extends Application {

  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
