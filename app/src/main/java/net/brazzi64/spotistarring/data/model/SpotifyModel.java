package net.brazzi64.spotistarring.data.model;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class SpotifyModel {

  public static final String OBJECT_TYPE_ALBUM = "album";
  public static final String OBJECT_TYPE_ARTIST = "artist";

  @StringDef({OBJECT_TYPE_ARTIST, OBJECT_TYPE_ALBUM})
  @Retention(RetentionPolicy.SOURCE)
  public @interface ObjectType {}
}
