package net.brazzi64.spotistarring.presenter;

import android.support.annotation.NonNull;

import net.brazzi64.spotistarring.data.AlbumRepository;
import net.brazzi64.spotistarring.data.RepositoryCallback;
import net.brazzi64.spotistarring.data.model.Album;

import java.util.List;


public class RecentAlbumsPresenter implements RecentAlbumsContract.Presenter {

  private RecentAlbumsContract.View view;

  public void attachView(@NonNull RecentAlbumsContract.View view) {
    this.view = view;
  }

  public void clearView() {
    this.view = null;
  }

  public void start() {

    // TODO: don't re-query if already there: ex, fragment rotation

    view.showLoadingIndicator();

    AlbumRepository albumRepository = new AlbumRepository();
    albumRepository.getNewReleases(new RepositoryCallback<List<Album>>() {
      @Override
      public void onFetchReady(@NonNull List<Album> data) {
        if (view != null) {
          view.hideLoadingIndicator();
          view.showRecentAlbums(data);
        }
      }

      @Override
      public void onFetchFailed(@NonNull Throwable throwable) {

      }
    });
  }

  @Override
  public void onAlbumStarClicked(@NonNull Album album) {

  }
}
