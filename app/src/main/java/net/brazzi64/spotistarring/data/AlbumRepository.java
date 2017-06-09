package net.brazzi64.spotistarring.data;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.data.model.responses.AlbumsByIdResponseBy;
import net.brazzi64.spotistarring.data.model.responses.NewReleasesResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;


public class AlbumRepository {

  private final StarredAlbumsStore starredAlbumsStore;
  private final SpotifyService service;

  @Inject
  public AlbumRepository(@NonNull StarredAlbumsStore starredAlbumsStore) {
    this.starredAlbumsStore = starredAlbumsStore;
    this.service = NetworkUtil.createSpotifyService();
  }

  public void fetchNewReleases(@NonNull RepositoryCallback<List<Album>> callback) {
    service.getNewReleases()
        .enqueue(new ResponseDelivery<NewReleasesResponse>(callback) {
          @NonNull
          @Override
          public List<Album> onInterceptSuccessfulResponse(@NonNull NewReleasesResponse response) {
            for (Album album : response.albums.items) {  // decorate
              album.starred = starredAlbumsStore.isAlbumStarred(album.id);
            }
            return response.albums.items;
          }
        });
  }

  public void fetchStarredAlbums(@NonNull RepositoryCallback<List<Album>> callback) {
    List<String> starredAlbumIds = starredAlbumsStore.getStarredAlbumsIds();
    service.getAlbumsById(TextUtils.join(",", starredAlbumIds))
        .enqueue(new ResponseDelivery<AlbumsByIdResponseBy>(callback) {
          @NonNull
          @Override
          public List<Album> onInterceptSuccessfulResponse(@NonNull AlbumsByIdResponseBy response) {
            return response.albums;
          }
        });
  }

  public void starAlbum(@NonNull Album album) {
    album.starred = true;
    starredAlbumsStore.addAlbum(album.id);
  }

  public void unstarAlbum(@NonNull Album album) {
    album.starred = false;
    starredAlbumsStore.removeAlbum(album.id);
  }


  private abstract static class ResponseDelivery<T> implements Callback<T> {

    private final RepositoryCallback<List<Album>> callback;

    private ResponseDelivery(@NonNull RepositoryCallback<List<Album>> callback) {
      this.callback = callback;
    }

    @NonNull
    public abstract List<Album> onInterceptSuccessfulResponse(@NonNull T response);

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull retrofit2.Response<T> response) {
      T responseBody = response.body();
      if (response.isSuccessful() && responseBody != null) {
        callback.onFetchReady(onInterceptSuccessfulResponse(responseBody));
      } else {
        callback.onFetchFailed(new Exception("Got a bad response - code=" + response.code()));
      }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
      callback.onFetchFailed(new Exception("Request failed", t));
    }
  }
}
