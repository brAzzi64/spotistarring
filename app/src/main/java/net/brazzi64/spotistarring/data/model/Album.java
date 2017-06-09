package net.brazzi64.spotistarring.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Album {

  @SerializedName("album_type")
  public final String albumType;

  public final List<Artist> artists;

  public final String href;

  public final String id;

  public final List<Image> images;

  public final String name;

  @SpotifyModel.ObjectType
  public final String type;

  public final String uri;

  public transient boolean starred;

  public Album(String albumType, List<Artist> artists, String href, String id, String name, String uri) {
    this.albumType = albumType;
    this.artists = artists;
    this.href = href;
    this.id = id;
    this.images = new ArrayList<>();
    this.name = name;
    this.type = SpotifyModel.OBJECT_TYPE_ALBUM;
    this.uri = uri;
  }
}
