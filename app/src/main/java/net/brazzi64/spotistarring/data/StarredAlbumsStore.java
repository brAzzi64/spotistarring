package net.brazzi64.spotistarring.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class StarredAlbumsStore {

  private static final String STARRED_ALBUMS_STORE = "starredAlbumsStore";
  private static final String STARRED_ALBUMS = "STARRED_ALBUMS";

  private final SharedPreferences sharedPreferences;

  private final List<String> starredAlbumIds = new ArrayList<>();

  @Inject
  public StarredAlbumsStore(@NonNull Context context) {
    sharedPreferences = context.getSharedPreferences(STARRED_ALBUMS_STORE, Context.MODE_PRIVATE);
    loadStarredAlbumIds();
  }

  public void addAlbum(@NonNull String albumId) {
    starredAlbumIds.add(albumId);
    saveAlbumIds();
  }

  public void removeAlbum(@NonNull String albumId) {
    starredAlbumIds.remove(albumId);
    saveAlbumIds();
  }

  public boolean isAlbumStarred(@NonNull String albumId) {
    return starredAlbumIds.contains(albumId);
  }

  @NonNull
  public List<String> getStarredAlbumsIds() {
    return starredAlbumIds;
  }

  private void loadStarredAlbumIds() {

    starredAlbumIds.clear();

    String raw = sharedPreferences.getString(STARRED_ALBUMS, "");
    String[] splitIds = raw.split(",");
    if (!(splitIds.length == 1 && splitIds[0].equals(""))) {
      Collections.addAll(starredAlbumIds, splitIds);
    }
  }

  private void saveAlbumIds() {
    sharedPreferences.edit().putString(STARRED_ALBUMS, TextUtils.join(",", starredAlbumIds)).apply();
  }
}
