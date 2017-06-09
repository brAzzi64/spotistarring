package net.brazzi64.spotistarring.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import net.brazzi64.spotistarring.BR;
import net.brazzi64.spotistarring.data.AlbumRepository;
import net.brazzi64.spotistarring.data.RepositoryCallback;
import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.infra.ViewModelBus;
import net.brazzi64.spotistarring.viewmodels.events.AlbumStarToggledEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RecentAlbumsViewModel extends BaseObservable {

  public final ObservableBoolean isLoading = new ObservableBoolean();
  public final ObservableBoolean gotError = new ObservableBoolean();

  private final AlbumRepository albumRepository;
  private List<Album> albums = new ArrayList<>();

  @Inject
  public RecentAlbumsViewModel(@NonNull AlbumRepository albumRepository) {

    this.albumRepository = albumRepository;
    this.isLoading.set(true);

    albumRepository.fetchNewReleases(new RepositoryCallback<List<Album>>() {
      @Override
      public void onFetchReady(@NonNull List<Album> data) {
        isLoading.set(false);
        albums = data;
        notifyPropertyChanged(BR.albums);
      }

      @Override
      public void onFetchFailed(@NonNull Throwable throwable) {
        isLoading.set(false);
        gotError.set(true);
      }
    });
  }

  @Bindable
  @NonNull
  public List<Album> getAlbums() {
    return albums;
  }

  public void onAlbumStarClicked(@NonNull Album album) {

    if (!album.starred) {
      albumRepository.starAlbum(album);
    } else {
      albumRepository.unstarAlbum(album);
    }

    ViewModelBus.getInstance().post(new AlbumStarToggledEvent(album, album.starred));
  }

  public void destroy() {

  }
}
