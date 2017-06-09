package net.brazzi64.spotistarring.data.model.responses;

import net.brazzi64.spotistarring.data.model.Album;

import java.util.List;


public class AlbumsByIdResponseBy {

  public final List<Album> albums;

  public AlbumsByIdResponseBy(List<Album> albums) {
    this.albums = albums;
  }
}
