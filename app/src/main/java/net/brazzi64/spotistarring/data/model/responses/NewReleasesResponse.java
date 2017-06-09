package net.brazzi64.spotistarring.data.model.responses;


import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.data.model.Page;

public class NewReleasesResponse {

  public final Page<Album> albums;

  public NewReleasesResponse(Page<Album> albums) {
    this.albums = albums;
  }
}
