package net.brazzi64.spotistarring.data;

import android.support.annotation.NonNull;

import net.brazzi64.spotistarring.data.model.Album;
import net.brazzi64.spotistarring.data.model.NewReleasesResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AlbumRepository {

  private static final String SPOTIFY_API_BASE_URL = "https://api.spotify.com";
  private static final String SPOTIFY_ACCESS_TOKEN = "BQDhlzbvCSpyfuMuZzGimZUr3jEWpymRQJOesrcTOLS6o6OYrHb6BVG9D7UYjdFHhBrcGr4XZFz9_An8VyR0xA";

  private final SpotifyService service;

  public AlbumRepository() {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(SPOTIFY_API_BASE_URL)
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    service = retrofit.create(SpotifyService.class);
  }

  public void getNewReleases(@NonNull RepositoryCallback<List<Album>> callback) {
    service.getNewReleases().enqueue(new ResponseDelivery(callback));
  }

  @NonNull
  private static OkHttpClient createOkHttpClient() {
    return new OkHttpClient.Builder()
        .addInterceptor(new AddAccessTokenHeaderInterceptor())
        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build();
  }

  private static class AddAccessTokenHeaderInterceptor implements Interceptor {

    AddAccessTokenHeaderInterceptor() { }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
      Request request = chain.request()
          .newBuilder()
          .addHeader("Authorization", "Bearer " + SPOTIFY_ACCESS_TOKEN)
          .build();

      return chain.proceed(request);
    }
  }

  private static class ResponseDelivery implements Callback<NewReleasesResponse> {

    private final RepositoryCallback<List<Album>> callback;

    private ResponseDelivery(@NonNull RepositoryCallback<List<Album>> callback) {
      this.callback = callback;
    }

    @Override
    public void onResponse(@NonNull Call<NewReleasesResponse> call, @NonNull retrofit2.Response<NewReleasesResponse> response) {
      NewReleasesResponse responseBody = response.body();
      if (response.isSuccessful() && responseBody != null) {
        callback.onFetchReady(responseBody.albums.items);
      } else {
        callback.onFetchFailed(new Exception("Got a bad response - code=" + response.code()));
      }
    }

    @Override
    public void onFailure(@NonNull Call<NewReleasesResponse> call, @NonNull Throwable t) {
      callback.onFetchFailed(new Exception("Request failed", t));
    }
  }
}
