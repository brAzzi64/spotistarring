package net.brazzi64.spotistarring.infra;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;


public class ViewModelBus {

  private ViewModelBus() {}

  @NonNull
  public static EventBus getInstance() {
    return EventBus.getDefault();
  }
}
