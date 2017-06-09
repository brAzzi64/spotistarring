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

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class StarredAlbumsViewModel extends BaseObservable {

  public final ObservableBoolean isLoading = new ObservableBoolean();
  public final ObservableBoolean gotError = new ObservableBoolean();

  private final AlbumRepository albumRepository;
  private List<Album> albums = new ArrayList<>();

  @Inject
  public StarredAlbumsViewModel(@NonNull AlbumRepository albumRepository) {
    this.albumRepository = albumRepository;
    ViewModelBus.getInstance().register(this);
    fetch();
  }

  private void fetch() {
    isLoading.set(true);
    albumRepository.fetchStarredAlbums(new RepositoryCallback<List<Album>>() {
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

  @Subscribe
  public void onEvent(@NonNull AlbumStarToggledEvent event) {
    fetch();
  }

  public void onAlbumStarClicked(@NonNull Album album) {
    // TODO: do
  }

  public void destroy() {
    ViewModelBus.getInstance().unregister(this);
  }
}
