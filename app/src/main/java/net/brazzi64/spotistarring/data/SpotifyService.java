package net.brazzi64.spotistarring.data;

import net.brazzi64.spotistarring.data.model.NewReleasesResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SpotifyService {

  @GET("/v1/browse/new-releases")
  Call<NewReleasesResponse> getNewReleases();
}
