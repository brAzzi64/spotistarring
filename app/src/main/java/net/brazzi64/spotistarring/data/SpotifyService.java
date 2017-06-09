package net.brazzi64.spotistarring.data;

import net.brazzi64.spotistarring.data.model.responses.AlbumsByIdResponseBy;
import net.brazzi64.spotistarring.data.model.responses.NewReleasesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SpotifyService {

  @GET("/v1/browse/new-releases")
  Call<NewReleasesResponse> getNewReleases();

  @GET("v1/albums")
  Call<AlbumsByIdResponseBy> getAlbumsById(@Query("ids") String commaSeparatedIds);
}
