package net.brazzi64.spotistarring.data;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkUtil {

  private static final String SPOTIFY_API_BASE_URL = "https://api.spotify.com";
  private static final String SPOTIFY_ACCESS_TOKEN = "BQCuIYnRNHKSod-hgn_dpC5X3xuLl3HY_GvwpKHdQ5034zV7g7ew78kEfwIh3_B2NzSqBX2gvbV-QHNtRKZ59g";

  private NetworkUtil() {}

  @NonNull
  public static SpotifyService createSpotifyService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(NetworkUtil.SPOTIFY_API_BASE_URL)
        .client(NetworkUtil.createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    return retrofit.create(SpotifyService.class);
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
}
