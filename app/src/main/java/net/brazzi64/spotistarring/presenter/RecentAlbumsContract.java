package net.brazzi64.spotistarring.presenter;

import android.support.annotation.NonNull;

import net.brazzi64.spotistarring.data.model.Album;

import java.util.List;


public interface RecentAlbumsContract {

  interface View {

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void showRecentAlbums(@NonNull List<Album> recentAlbums);
  }

  interface Presenter {

    void onAlbumStarClicked(@NonNull Album album);
  }
}
