package net.brazzi64.spotistarring.data.model;


public class Artist {

  public final String href;

  public final String id;

  public final String name;

  @SpotifyModel.ObjectType
  public final String type;

  public final String uri;

  public Artist(String href, String id, String name, String uri) {
    this.href = href;
    this.id = id;
    this.name = name;
    this.type = SpotifyModel.OBJECT_TYPE_ALBUM;
    this.uri = uri;
  }
}
