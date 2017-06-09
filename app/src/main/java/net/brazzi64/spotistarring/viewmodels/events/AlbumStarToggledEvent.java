package net.brazzi64.spotistarring.viewmodels.events;

import android.support.annotation.NonNull;

import net.brazzi64.spotistarring.data.model.Album;


public class AlbumStarToggledEvent {

  public final Album album;
  public final boolean starred;

  public AlbumStarToggledEvent(@NonNull Album album, boolean starred) {
    this.album = album;
    this.starred = starred;
  }
}