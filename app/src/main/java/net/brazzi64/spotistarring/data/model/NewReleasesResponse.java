package net.brazzi64.spotistarring.data.model;


public class NewReleasesResponse {

  public final Page<Album> albums;

  public NewReleasesResponse(Page<Album> albums) {
    this.albums = albums;
  }
}
