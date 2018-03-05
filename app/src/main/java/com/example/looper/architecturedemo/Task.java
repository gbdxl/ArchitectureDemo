package com.example.looper.architecturedemo;

import android.widget.Toast;

public class Task {

  private int position;

  public Task(int position) {
    this.position = position;
  }

  public void showToast() {
    Toast.makeText(App.mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
  }
}