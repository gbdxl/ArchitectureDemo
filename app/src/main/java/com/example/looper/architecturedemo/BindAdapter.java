package com.example.looper.architecturedemo;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by looper on 2018/3/4.
 */

public class BindAdapter {

  @BindingAdapter({"url"})
  public static void loadImage(ImageView view, String url) {
    Glide.with(view.getContext()).load(url).into(view);
  }
}
